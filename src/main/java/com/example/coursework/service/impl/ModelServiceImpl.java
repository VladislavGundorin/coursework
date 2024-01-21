package com.example.coursework.service.impl;

import com.example.coursework.dto.ModelDTO;
import com.example.coursework.model.Model;
import com.example.coursework.repositorie.ModelRepository;
import com.example.coursework.service.ModelService;
import com.example.coursework.validation.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@EnableCaching
public class ModelServiceImpl implements ModelService {
    private final ModelMapper modelMapper;
    private final ModelRepository modelRepository;
    private final ValidationUtil validationUtil;

    @Autowired
    public ModelServiceImpl(ModelMapper modelMepper, ModelRepository modelRepository, ValidationUtil validationUtil) {
        this.modelMapper = modelMepper;
        this.modelRepository = modelRepository;
        this.validationUtil = validationUtil;
    }

    @Override
    public ModelDTO createModel(ModelDTO modelDTO) {
        Model model = modelMapper.map(modelDTO, Model.class);
        return modelMapper.map(modelRepository.save(model),ModelDTO.class);
    }

    @Override
    public void deleteModelById(UUID modelId) {
        modelRepository.deleteById(modelId);
    }

    @Cacheable(value = "Allmodels")
    @Override
    public List<ModelDTO> getAllModels() {
        List<Model> models = modelRepository.findAll();
        return models.stream().map(model -> modelMapper.map(model,ModelDTO.class))
                .collect(Collectors.toList());
    }
    @Override
    public ModelDTO getModelById(UUID id) {
        Optional<Model> modelOptional = modelRepository.findById(id);
        if (modelOptional.isPresent()) {
            return modelMapper.map(modelOptional.get(), ModelDTO.class);
        }
        return null;
    }

    @Override
    public List<ModelDTO> getModelsByBrandAndName(String brandName, String modelName) {
        List<Model> models = modelRepository.findByBrandNameAndModelName(brandName,modelName);
        return models.stream().map(model -> modelMapper.map(model,ModelDTO.class))
                .collect(Collectors.toList());
    }
    @Override
    public List<ModelDTO> getModelsByBrandName(String brandName) {
        List<Model> models = modelRepository.findByName(brandName);
        return models.stream()
                .map(model -> modelMapper.map(model, ModelDTO.class))
                .collect(Collectors.toList());
    }
}