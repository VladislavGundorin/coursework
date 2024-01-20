package com.example.coursework.service.impl;
import com.example.coursework.enums.Role;
import com.example.coursework.model.User;
import com.example.coursework.repositorie.UserRepository;
import com.example.coursework.repositorie.UserRoleRepository;
import com.example.coursework.service.UserRoleService;
import com.example.coursework.service.UserService;
import com.example.coursework.views.UserRegistrationViewModel;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private UserRepository userRepository;

    private UserRoleRepository userRoleRepository;
    private ModelMapper modelMapper;
    private UserRoleService userRoleService;
    private UserService userService;
    private PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, UserRoleRepository userRoleRepository, PasswordEncoder passwordEncoder, ModelMapper modelMapper,UserRoleService userRoleService,UserService userService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userRoleRepository = userRoleRepository;
        this.modelMapper = modelMapper;
        this.userService=userService;
        this.userRoleService=userRoleService;
    }


    public void registerUser(UserRegistrationViewModel userRegistrationViewModel) {
        User user= modelMapper.map(userRegistrationViewModel, User.class);
        user.setRole(userRoleService.getByRole(Role.USER));
        user.setActive(true);
        user.setImageUrl("blank");
        user.setPassword(passwordEncoder.encode(userRegistrationViewModel.getPassword()));
        userRepository.saveAndFlush(user);
    }
    public void authWithHttpServletRequest(HttpServletRequest request, String username, String password) {
        try {
            request.login(username, password);
        } catch (ServletException e) {

        }
    }

    public User getUser(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username + " was not found!"));
    }
}