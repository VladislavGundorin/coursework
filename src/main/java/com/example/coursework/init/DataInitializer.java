package com.example.coursework.init;

import com.example.coursework.dto.*;
import com.example.coursework.enums.Category;
import com.example.coursework.enums.Engine;
import com.example.coursework.enums.Role;
import com.example.coursework.enums.Transmission;
import com.example.coursework.model.*;
import com.example.coursework.repositorie.*;
import com.example.coursework.service.*;
import com.example.coursework.service.impl.AuthService;
import com.example.coursework.views.UserRegistrationViewModel;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Component
public class DataInitializer implements CommandLineRunner {
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final OfferRepository offerRepository;
    private final ModelRepository modelRepository;
    private final BrandRepository brandRepository;
    private final UserService userService;
    private final UserRoleService userRoleService;
    private final OfferService offerService;
    private final ModelService modelService;
    private final BrandService brandService;
    private final AuthService authService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public DataInitializer(UserRepository userRepository, UserRoleRepository userRoleRepository, OfferRepository offerRepository, ModelRepository modelRepository, BrandRepository brandRepository, UserService userService, UserRoleService userRoleService, OfferService offerService, ModelService modelService, BrandService brandService, AuthService authService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.offerRepository = offerRepository;
        this.modelRepository = modelRepository;
        this.brandRepository = brandRepository;
        this.userService = userService;
        this.userRoleService = userRoleService;
        this.offerService = offerService;
        this.modelService = modelService;
        this.brandService = brandService;
        this.authService = authService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        UserRole userRoleUser = new UserRole(Role.USER);
        UserRole userRoleAdmin = new UserRole(Role.ADMIN);
        userRoleRepository.save(userRoleUser);
        userRoleRepository.save(userRoleAdmin);

        List<UserRoleDTO> userRoleDTOS = userRoleService.getAllUserRoles();

        Faker faker = new Faker();
        Random random = new Random();

        UserDTO adminDTO = new UserDTO();
        adminDTO.setUsername("admin");
        adminDTO.setPassword("admin");
        adminDTO.setFirstName("Admin");
        adminDTO.setLastName("Admin");
        adminDTO.setActive(true);
        adminDTO.setImageUrl("/path/to/admin/image");
        adminDTO.setRole(userRoleDTOS.get(1));

        User admin = new User();
        admin.setUsername(adminDTO.getUsername());
        admin.setPassword(passwordEncoder.encode(adminDTO.getPassword()));
        admin.setFirstName(adminDTO.getFirstName());
        admin.setLastName(adminDTO.getLastName());
        admin.setActive(adminDTO.isActive());
        admin.setImageUrl(adminDTO.getImageUrl());
        admin.setRole(userRoleRepository.findById(adminDTO.getRole().getId()).orElseThrow());

        userRepository.save(admin);

//        UserRegistrationViewModel adminUser = new UserRegistrationViewModel();
//        adminUser.setUsername("admin");
//        adminUser.setPassword("admin");
//        adminUser.setFirstName("Admin");
//        adminUser.setLastName("User");
//
//        authService.registerUser(adminUser);

        for (int i = 0; i < 100; i++) {
            UserDTO userDTO = new UserDTO();
            userDTO.setUsername(faker.name().username());
            userDTO.setPassword(faker.internet().password());
            userDTO.setFirstName(faker.name().firstName());
            userDTO.setLastName(faker.name().lastName());
            userDTO.setActive(random.nextBoolean());
            userDTO.setImageUrl(faker.internet().image());
            userDTO.setPhone_number(faker.phoneNumber().phoneNumber());
            userDTO.setRole(userRoleDTOS.get(random.nextInt(2)));

            User user = new User();
            user.setUsername(userDTO.getUsername());
            user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            user.setFirstName(userDTO.getFirstName());
            user.setLastName(userDTO.getLastName());
            user.setActive(userDTO.isActive());
            user.setImageUrl(userDTO.getImageUrl());
            user.setPhone_number(userDTO.getPhone_number());
            user.setRole(userRoleRepository.findById(userDTO.getRole().getId()).orElseThrow());

            userRepository.save(user);
        }

        List<UserDTO> userDTOList = userService.getAllUsers();
        String[] carBrands = {"BMW", "Audi", "Honda", "Ford", "HAVAL"};

        for (String brandName : carBrands) {
            Brand brand = new Brand();
            brand.setName(brandName);
            brandRepository.save(brand);

            if (brandName.equals("BMW")) {
                modelRepository.save(new Model("3 Series", Category.CAR, 2018, 2022, "/picture/x3_series.jpg", brand));
                modelRepository.save(new Model("X5", Category.CAR, 2019, 2022, "/picture/x5.jpg", brand));
                modelRepository.save(new Model("M5", Category.CAR, 2019, 2023, "/picture/bmw m5.jpg", brand));
                modelRepository.save(new Model("M1", Category.CAR, 2017, 2021, "/picture/bmw m1.jpg", brand));
                modelRepository.save(new Model("M2", Category.CAR, 2016, 2023, "/picture/m2.jpg", brand));
                modelRepository.save(new Model("X3", Category.CAR, 2015, 2022, "/picture/bmw_x3.jpg", brand));
            } else if (brandName.equals("Audi")) {
                modelRepository.save(new Model("A4", Category.CAR, 2015, 2022, "/picture/audi a4.jpeg", brand));
                modelRepository.save(new Model("Q5", Category.CAR, 2016, 2022, "/picture/audi q5.jpg", brand));
                modelRepository.save(new Model("A3", Category.CAR, 2013, 2022, "/picture/audi_a3.jpg", brand));
                modelRepository.save(new Model("RS 6", Category.CAR, 2018, 2023, "/picture/audi_rs6.jpg", brand));
            } else if (brandName.equals("Honda")) {
                modelRepository.save(new Model("Legend", Category.CAR, 2000, 2022, "/picture/honda legend.jpg", brand));
                modelRepository.save(new Model("Saber", Category.CAR, 1995, 2003, "/picture/honda saber.jpg", brand));
                modelRepository.save(new Model("CR-V", Category.CAR, 1997, 2023, "/picture/honda c-rv.jpg", brand));
            } else if (brandName.equals("Ford")) {
                modelRepository.save(new Model("C-MAX", Category.CAR, 2012, 2023, "/picture/FordC-MAX.jpg", brand));
                modelRepository.save(new Model("ECOSPORT", Category.CAR, 2013, 2023, "/picture/ford ecospor.jpg", brand));
            } else if (brandName.equals("HAVAL")) {
                modelRepository.save(new Model("HAVAL M6", Category.CAR, 2019, 2023, "/picture/Haval_H6.jpg", brand));
                modelRepository.save(new Model("HAVAL JOLION", Category.CAR, 2020, 2023, "/picture/haval jolian.jpg", brand));
            }
        }

        List<ModelDTO> modelDTOList = modelService.getAllModels();

        String commonImageUrl = "/picture/offer.png";
        for (int i = 0; i < 100; i++) {
            OfferDTO offerDTO = new OfferDTO();
            offerDTO.setDescription(faker.lorem().sentence());
            offerDTO.setEngine(Engine.values()[faker.random().nextInt(Engine.values().length)]);
            offerDTO.setMileage(faker.random().nextInt(1000, 100000));
            offerDTO.setPrice(faker.random().nextInt(900000, 5000000));
            offerDTO.setTransmission(Transmission.values()[faker.random().nextInt(Transmission.values().length)]);
            offerDTO.setYear(faker.random().nextInt(2016, 2020));
            offerDTO.setSeller(userDTOList.get(random.nextInt(userDTOList.size())));
            offerDTO.setModel(modelDTOList.get(random.nextInt(modelDTOList.size())));
            offerDTO.setImageUrl(commonImageUrl);

            Offer offer = new Offer();
            offer.setDescription(offerDTO.getDescription());
            offer.setEngine(offerDTO.getEngine());
            offer.setMileage(offerDTO.getMileage());
            offer.setPrice(offerDTO.getPrice());
            offer.setTransmission(offerDTO.getTransmission());
            offer.setYear(offerDTO.getYear());
            offer.setSeller(userRepository.findById(offerDTO.getSeller().getId()).orElseThrow());
            offer.setModel(modelRepository.findById(offerDTO.getModel().getId()).orElseThrow());
            offer.setImageUrl(commonImageUrl);


            offerRepository.save(offer);
        }

    }
}