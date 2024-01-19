package com.example.coursework.service;

import com.example.coursework.dto.BrandDTO;

import java.util.List;
import java.util.UUID;

public interface BrandService {
    BrandDTO createBrand(BrandDTO brandDTO);

    void deleteBrandById(UUID id);

    List<BrandDTO> getAllBrands();

//    BrandDTO getBrandById(UUID id);

//    BrandDTO updateBrandById(UUID id,BrandDTO brandDTO);

    List<BrandDTO> getBrandByName(String name);

    List<Object[]> getBrandWithLowestMileageAndPrice();

}

