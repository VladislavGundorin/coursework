package com.example.coursework.service.impl;

import com.example.coursework.dto.UserRoleDTO;
import com.example.coursework.enums.Role;
import com.example.coursework.model.UserRole;
import com.example.coursework.repositorie.UserRoleRepository;
import com.example.coursework.service.UserRoleService;
import com.example.coursework.validation.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserRoleServiceImpl implements UserRoleService {
    private final ModelMapper modelMapper;
    private final UserRoleRepository userRoleRepository;
    private final ValidationUtil validationUtil;

    @Autowired
    public UserRoleServiceImpl(ModelMapper modelMapper, UserRoleRepository userRoleRepository, ValidationUtil validationUtil) {
        this.modelMapper = modelMapper;
        this.userRoleRepository = userRoleRepository;
        this.validationUtil = validationUtil;
    }

    @Override
    public UserRoleDTO createUserRole(UserRoleDTO userRoleDTO) {
        UserRole userRole = modelMapper.map(userRoleDTO, UserRole.class);
        return modelMapper.map(userRoleRepository.save(userRole),UserRoleDTO.class);
    }

    @Override
    public void deleteUserRole(UUID userRoleId) {
        userRoleRepository.deleteById(userRoleId);
    }

    @Override
    public List<UserRoleDTO> getAllUserRoles() {
        List<UserRole> userRoles = userRoleRepository.findAll();
        return userRoles.stream().map(userRole -> modelMapper.map(userRole,UserRoleDTO.class))
                .collect(Collectors.toList());
    }
    @Override
    public Optional<UserRoleDTO> getUserRoleByID(UUID id) {
        Optional<UserRole> optionalUserRoleDTO = userRoleRepository.findById(id);
        return optionalUserRoleDTO.map(userRole -> modelMapper.map(userRole, UserRoleDTO.class));
    }
    @Override
    public List<Object[]> getActiveUsersWithRoles() {
        return userRoleRepository.findActiveUsersWithRoles();
    }
    @Override
    public UserRole getByRole(Role role) {
        return userRoleRepository.findByRole(role);
    }
}
