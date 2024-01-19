package com.example.coursework.service;

import com.example.coursework.dto.UserRoleDTO;
import com.example.coursework.enums.Role;
import com.example.coursework.model.UserRole;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRoleService {
    UserRoleDTO createUserRole(UserRoleDTO userRoleDTO);

    void deleteUserRole (UUID id);


    List<UserRoleDTO> getAllUserRoles();

    Optional<UserRoleDTO> getUserRoleByID(UUID id);

    List<Object[]> getActiveUsersWithRoles();

    UserRole getByRole(Role role);


//    UserRoleDTO updateUserRoleById(UUID id,UserRoleDTO userRoleDTO);

}
