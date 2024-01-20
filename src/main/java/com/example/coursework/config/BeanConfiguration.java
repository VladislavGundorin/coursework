package com.example.coursework.config;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public ModelMapper mapper() {
        return new ModelMapper();
    }
    @Bean
    public Validator validator(){
        return Validation
                .buildDefaultValidatorFactory()
                .getValidator();
    }
//    @Bean
//    public UniqueUsernameValidator uniqueUsernameValidator(UserRepository userRepository) {
//        return new UniqueUsernameValidator(userRepository);
//    }

}
