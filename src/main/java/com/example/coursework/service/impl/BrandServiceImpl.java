package com.example.coursework.service.impl;

import com.example.coursework.dto.BrandDTO;
import com.example.coursework.model.Brand;
import com.example.coursework.repositorie.BrandRepository;
import com.example.coursework.service.BrandService;
import com.example.coursework.validation.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BrandServiceImpl implements BrandService {
    private final ModelMapper modelMapper;
    private final BrandRepository brandRepository;
    private final ValidationUtil validationUtil;

    @Autowired
    public BrandServiceImpl(BrandRepository brandRepository, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.brandRepository = brandRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public BrandDTO createBrand(BrandDTO brandDTO) {
        Brand brand = modelMapper.map(brandDTO, Brand.class);
        return modelMapper.map(brandRepository.save(brand), BrandDTO.class);
    }

    @Override
    public void deleteBrandById(UUID brandId) {
        brandRepository.deleteById(brandId);
    }

    @Override
    public List<BrandDTO> getAllBrands() {
        List<Brand> brands = brandRepository.findAll();
        return brands.stream().map(brand -> modelMapper.map(brand, BrandDTO.class))
                .collect(Collectors.toList());
    }
    @Override
    public List<Object[]> getBrandWithLowestMileageAndPrice() {
        return brandRepository.findBrandWithLowestMileageAndPrice();
    }
    @Override
    public List<BrandDTO> getBrandByName(String name) {
        List<Brand> brands = brandRepository.findByName(name);
        return brands.stream()
                .map(brand -> modelMapper.map(brand, BrandDTO.class))
                .collect(Collectors.toList());
    }
}


