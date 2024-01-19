package com.example.coursework.service;

import com.example.coursework.dto.UserDTO;
import com.example.coursework.enums.Role;
import com.example.coursework.model.Offer;
import com.example.coursework.model.User;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {
    UserDTO createUser (UserDTO userDTO);

    void deleteUserById(UUID id);

    List<UserDTO> getAllUsers();

    Optional<UserDTO> getUserById(UUID id);

    List<Object[]> getUsersByRole(@Param("roleValue") Role role);

    User getUserByUsername(String username);

        List<Offer> allUserOffers(String username);

//    UserDTO updateUser(UUID id, UserDTO userDTO);


    List<User> getByFirstNameAndLastName(String firstName, String lastName);



}
