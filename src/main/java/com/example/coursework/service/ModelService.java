package com.example.coursework.service;

import com.example.coursework.dto.ModelDTO;

import java.util.List;
import java.util.UUID;

public interface ModelService {
    ModelDTO createModel(ModelDTO modelDTO);

    void deleteModelById(UUID id);

    List<ModelDTO> getAllModels();

    ModelDTO getModelById(UUID id);

    List<ModelDTO> getModelsByBrandName(String brandName);

    List<ModelDTO> getModelsByBrandAndName(String brandName, String modelName);

//    ModelDTO updateModelById(UUID id, ModelDTO modelDTO);

//    List<String> getModelsByBrandAndStartYear(String brandName,int yearstart);

//    List<ModelDTO> getModelsByBrandName(String brandName);


}
