package com.example.coursework.service.impl;

import com.example.coursework.dto.UserDTO;
import com.example.coursework.enums.Role;
import com.example.coursework.model.Offer;
import com.example.coursework.model.User;
import com.example.coursework.model.UserRole;
import com.example.coursework.repositorie.OfferRepository;
import com.example.coursework.repositorie.UserRepository;
import com.example.coursework.repositorie.UserRoleRepository;
import com.example.coursework.service.UserRoleService;
import com.example.coursework.service.UserService;
import com.example.coursework.validation.ValidationUtil;
import com.example.coursework.views.OfferViewModel;
import com.example.coursework.views.Profile;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.beans.Transient;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final UserRoleRepository userRoleRepository;
    private final UserRoleService userRoleService;
    //    private final PasswordEncoder passwordEncoder;
    private final OfferRepository offerRepository;

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, ValidationUtil validationUtil, UserRoleRepository userRoleRepository, UserRoleService userRoleService, OfferRepository offerRepository) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.userRoleRepository = userRoleRepository;
        this.userRoleService = userRoleService;
//        this.passwordEncoder = passwordEncoder;
        this.offerRepository = offerRepository;
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user = modelMapper.map(userDTO, User.class);
        return modelMapper.map(userRepository.save(user), UserDTO.class);
    }

    @Override
    public void deleteUserById(UUID userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(user -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<UserDTO> getUserById(UUID id) {
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.map(user -> modelMapper.map(user, UserDTO.class));
    }

    @Override
    public List<Object[]> getUsersByRole(Role role) {
        return userRepository.findUsersByRole(role);
    }

    //    @Override
//    public User getUserByUsername(String username) {
//        Optional<User> users = userRepository.findUserByUsername(username);
//        return users.get();
//    }
    @Override
    public User getUserByUsername(String username) {
        return userRepository.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь с именем " + username + " не найден"));
    }

    @Override
    public List<Offer> allUserOffers(String username) {
        Optional<User> userOptional = userRepository.findUserByUsername(username);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return offerRepository.findBySeller(user);
        }
        return Collections.emptyList();
    }

    @Override
    public List<User> getByFirstNameAndLastName(String firstName, String lastName) {
        return userRepository.findByFirstNameAndLastName(firstName, lastName);
    }
    @Transactional
    @Override
    public void updateUserProfile(Profile updatedProfile) {
        Optional<User> optionalUser = userRepository.findUserByUsername(updatedProfile.getUsername());
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setFirstName(updatedProfile.getFirstName());
            user.setLastName(updatedProfile.getLastName());
            user.setPhone_number(updatedProfile.getPhone_number());
            userRepository.save(user);
        }
    }
}



