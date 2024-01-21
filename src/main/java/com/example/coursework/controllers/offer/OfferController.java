package com.example.coursework.controllers.offer;
import com.example.coursework.dto.OfferDTO;
import com.example.coursework.service.OfferService;
import com.example.coursework.views.OfferViewModel;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@Controller
@RequestMapping("/offers")
public class OfferController {
    private static final Logger LOG = LogManager.getLogger(Controller.class);

    private final OfferService offerService;

    @Autowired
    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    @GetMapping("/alloffers")
    public String getAllOffers(Model model) {
        LOG.log(Level.INFO, "Show all offers for ");
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        List<OfferDTO> offerDTOs = offerService.getAllOffersOrderByViewCountDesc();

        stopWatch.stop();
        System.out.println("getAllOffers execution time: " + stopWatch.getTotalTimeMillis() + " ms");

        model.addAttribute("offers", offerDTOs);
        return "offers-all";
    }


    @GetMapping("/details/{offer-id}")
    public String offerDetails(@PathVariable("offer-id") UUID offerId, Model model) {
        LOG.log(Level.INFO, "Entering method: offerDetails for offer " + offerId);
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        OfferDTO offerDTO = offerService.getOfferById(offerId);
        offerService.incrementViewCount(offerId);
        stopWatch.stop();
        System.out.println("offerDetails execution time: " + stopWatch.getTotalTimeMillis() + " ms");
        model.addAttribute("offerDetails", offerDTO);
        return "offers-details";
    }

    @DeleteMapping("/delete/offer/{id}")
    public void deleteOfferById(@PathVariable UUID id) {
        LOG.log(Level.INFO, "Entering method: deleteOfferById for offer " + id);
        offerService.deleteOfferById(id);
    }

    @GetMapping("/offer-view")
    public String getOfferView(Model model) {
        LOG.log(Level.INFO, "Entering method: getOfferView");
        List<OfferViewModel> offers = offerService.getOfferDataForUserView();
        model.addAttribute("offers", offers);
        return "OfferViewModel";
    }

    @PostMapping("/delete/offers/{id}")
    public String deleteOffer(@PathVariable UUID id) {
        LOG.log(Level.INFO, "Entering method: deleteOffer for offer " + id);
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        offerService.deleteOfferById(id);
        stopWatch.stop();
        System.out.println("deleteOfferById execution time: " + stopWatch.getTotalTimeMillis() + " ms");
        return "redirect:/offers/alloffers";
    }
    @GetMapping("/create")
    public String createOffer(){
        LOG.log(Level.INFO, "Entering method: createOffer");
        return "offers-add";
    }
    @ModelAttribute("offerDTO")
    public OfferDTO initOffer(){
        LOG.log(Level.INFO, "Entering method: initOffer");
        return new OfferDTO();
    }
    @PostMapping("/create")
    public String createOffer(@ModelAttribute("offerDTO") OfferDTO offerDTO) {
        LOG.log(Level.INFO, "Entering method: createOffer");
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        offerService.createOffer(offerDTO);
        stopWatch.stop();
        System.out.println("createOffer execution time: " + stopWatch.getTotalTimeMillis() + " ms");

        return "redirect:/offers/create";
    }
    @GetMapping("/top")
    public String getTopOffers(Model model) {
        List<OfferDTO> topOffers = offerService.getTopOffers(offerService.getAllOffers());
        model.addAttribute("topOffers", topOffers);
        return "top-offers";
    }

}
