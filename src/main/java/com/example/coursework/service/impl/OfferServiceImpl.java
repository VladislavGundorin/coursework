package com.example.coursework.service.impl;

import com.example.coursework.dto.OfferDTO;
import com.example.coursework.model.Brand;
import com.example.coursework.model.Model;
import com.example.coursework.model.Offer;
import com.example.coursework.repositorie.BrandRepository;
import com.example.coursework.repositorie.ModelRepository;
import com.example.coursework.repositorie.OfferRepository;
import com.example.coursework.repositorie.UserRepository;
import com.example.coursework.service.OfferService;
import com.example.coursework.validation.ValidationUtil;
import com.example.coursework.views.OfferViewModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OfferServiceImpl implements OfferService {
    private final ModelMapper modelMapper;
    private final OfferRepository offerRepository;
    private final ValidationUtil validationUtil;
    private final ModelRepository modelRepository;
    private final BrandRepository brandRepository;
    private final UserRepository userRepository;

    @Autowired
    public OfferServiceImpl(ModelMapper modelMapper, OfferRepository offerRepository, ValidationUtil validationUtil, ModelRepository modelRepository, BrandRepository brandRepository, UserRepository userRepository) {
        this.modelMapper = modelMapper;
        this.offerRepository = offerRepository;
        this.validationUtil = validationUtil;
        this.modelRepository = modelRepository;
        this.brandRepository = brandRepository;
        this.userRepository = userRepository;
    }

    @Override
    public OfferDTO createOffer(OfferDTO offerDTO) {
        Offer offer = modelMapper.map(offerDTO, Offer.class);
        return modelMapper.map(offerRepository.save(offer),OfferDTO.class);
    }

    @Override
    public void deleteOfferById(UUID offerId) {
        offerRepository.deleteById(offerId);
    }

    @Override
    public List<OfferDTO> getAllOffers() {
        List<Offer> offers = offerRepository.findAll();
        return offers.stream().map(offer -> modelMapper.map(offer,OfferDTO.class))
                .collect(Collectors.toList());
    }
    @Override
    public OfferDTO getOfferById(UUID id) {
        Optional<Offer> offerOptional = offerRepository.findById(id);
        if (offerOptional.isPresent()) {
            return modelMapper.map(offerOptional.get(), OfferDTO.class);
        }
        return null;
    }
    @Override
    public List<OfferDTO> getAllOffersOrderByViewCountDesc() {
        List<Offer> offers = offerRepository.findAllByOrderByViewCountDesc();
        return offers.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    private OfferDTO convertToDTO(Offer offer) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(offer, OfferDTO.class);
    }
    @Override
    public void incrementViewCount(UUID offerId) {
        Offer offer = offerRepository.findById(offerId).orElse(null);
        if (offer != null) {
            offer.setViewCount(offer.getViewCount() + 1);
            offerRepository.save(offer);
        }
    }
    @Override
    public List<OfferViewModel> getOfferDataForUserView() {
        List<Offer> offers = offerRepository.findAll();
        List<OfferViewModel> offerViewModels = new ArrayList<>();

        for (Offer offer : offers) {
            Brand brand = offer.getModel().getBrand();
            Model model = offer.getModel();

            OfferViewModel viewModel = new OfferViewModel(

                    offer.getId(),
                    brand.getName(),
                    model.getName(),
                    offer.getPrice(),
                    model.getImageUrl()
            );

            offerViewModels.add(viewModel);
        }
        return offerViewModels;
    }
}
