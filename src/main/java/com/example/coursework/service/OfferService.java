package com.example.coursework.service;

import com.example.coursework.dto.OfferDTO;
import com.example.coursework.views.AllOfferUserModelBrandAdd;
import com.example.coursework.views.OfferViewModel;

import java.util.List;
import java.util.UUID;

public interface OfferService {
    OfferDTO createOffer (OfferDTO offerDTO);

    void deleteOfferById(UUID id);


    List<OfferDTO> getAllOffers();


    OfferDTO getOfferById(UUID id);

    List<OfferDTO> getAllOffersOrderByViewCountDesc();

    void incrementViewCount(UUID offerId);

    List<OfferViewModel> getOfferDataForUserView();



//    OfferDTO updateOfferByID(UUID id,OfferDTO offerDTO);

//    OfferDTO updateOfferAllFields (UUID id, OfferDTO offerDTO);

//    List<String> getDescriptionsByBrandAndModel(String brandName, String modelName);


//    List<String> getOffersWithKeyword(@Param("keyword") String keyword);
}

