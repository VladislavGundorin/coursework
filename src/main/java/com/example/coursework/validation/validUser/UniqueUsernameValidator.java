//package com.example.coursework.validation.validUser;
//
//
//import com.example.coursework.repositorie.UserRepository;
//import jakarta.validation.ConstraintValidator;
//import jakarta.validation.ConstraintValidatorContext;
//import org.springframework.beans.factory.annotation.Autowired;
//
//public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    public UniqueUsernameValidator(UserRepository userRepository){
//        this.userRepository = userRepository;
//    }
//
//    public UniqueUsernameValidator(){
//
//    }
//
//    @Override
//    public boolean isValid(String name, ConstraintValidatorContext context) {
//        return userRepository.findUserByUsername(name).isEmpty();
//    }
//    @Autowired
//    public void setUserRepository(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//}