package com.example.coursework.service;

import com.example.coursework.dto.ModelDTO;

import java.util.List;
import java.util.UUID;

public interface ModelService {
    ModelDTO createModel(ModelDTO modelDTO);

    void deleteModelById(UUID id);

    List<ModelDTO> getAllModels();

//    List<ModelDTO> getModelsByBrandName(String brandName);


    ModelDTO getModelById(UUID id);

    List<ModelDTO> getModelsByBrandName(String brandName);

//    ModelDTO updateModelById(UUID id, ModelDTO modelDTO);

//    List<String> getModelsByBrandAndStartYear(String brandName,int yearstart);


    List<ModelDTO> getModelsByBrandAndName(String brandName, String modelName);
}
