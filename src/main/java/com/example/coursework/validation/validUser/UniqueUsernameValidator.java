package com.example.coursework.validation.validUser;


import com.example.coursework.repositorie.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {


    private UserRepository userRepository;
    @Autowired
    public UniqueUsernameValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return userRepository.findUserByUsername(value).isEmpty();
    }
}