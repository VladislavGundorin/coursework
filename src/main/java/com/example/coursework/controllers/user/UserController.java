package com.example.coursework.controllers.user;
import com.example.coursework.dto.UserDTO;
import com.example.coursework.enums.Role;
import com.example.coursework.model.User;
import com.example.coursework.service.OfferService;
import com.example.coursework.service.UserService;
import com.example.coursework.service.impl.AuthService;
import com.example.coursework.views.Profile;
import com.example.coursework.views.UserRegistrationViewModel;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/users")
public class UserController {
    private static final Logger LOG = LogManager.getLogger(Controller.class);
    private final UserService userService;
    private final AuthService authService;
    private final OfferService offerService;

    @Autowired
    public UserController(UserService userService, AuthService authService, OfferService offerService) {
        this.userService = userService;
        this.authService = authService;
        this.offerService = offerService;
    }

    @GetMapping("/getuser/{id}")
    public UserDTO getUserById(@PathVariable UUID id) {
        LOG.log(Level.INFO, "Entering method: getUserById");
        return userService.getUserById(id).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable UUID id) {
        LOG.log(Level.INFO, "Entering method: deleteUserById");
        userService.deleteUserById(id);
    }

    @GetMapping("/byRole")
    public List<Object[]> getUsersByRole(@RequestParam Role role) {
        LOG.log(Level.INFO, "Entering method: getUsersByRole");
        return userService.getUsersByRole(role);
    }

    @PostMapping("/create")
    public UserDTO createUser(@RequestBody UserDTO userDTO) {
        LOG.log(Level.INFO, "Entering method: createUser");
        return userService.createUser(userDTO);
    }

    @GetMapping("/allusers")
    public String getAllUser(Model model) {
        LOG.log(Level.INFO, "Entering method: getAllUser");
        List<UserDTO> userDTOs = userService.getAllUsers();
        model.addAttribute("users", userDTOs);
        return "users-all";
    }

    @GetMapping("/login")
    public String login() {
        LOG.log(Level.INFO, "Entering method: login");
        return "login";
    }

    @PostMapping("/login-error")
    public String onFailedLogin(
            @ModelAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY) String username,
            RedirectAttributes redirectAttributes) {
        LOG.log(Level.INFO, "Entering method: onFailedLogin");
        redirectAttributes.addFlashAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY, username);
        redirectAttributes.addFlashAttribute("badCredentials", true);

        return "redirect:/users/login";
    }

    @PostMapping("/register")
    public String registerUser(@Valid UserRegistrationViewModel userRegistrationViewModel, BindingResult bindingResult, RedirectAttributes redirectAttributes, HttpServletRequest request) {
        LOG.log(Level.INFO, "Entering method: registerUser");
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("regUserView", userRegistrationViewModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.regUserView", bindingResult);
            return "redirect:/users/register";
        }

        authService.registerUser(userRegistrationViewModel);
        authService.authWithHttpServletRequest(request, userRegistrationViewModel.getUsername(), userRegistrationViewModel.getPassword());

        return "redirect:/";
    }

    @ModelAttribute("regUserView")
    public UserRegistrationViewModel initUser() {

        return new UserRegistrationViewModel();
    }

    @GetMapping("/register")
    public String regNewUSer() {
        LOG.log(Level.INFO, "Entering method: regNewUSer");
        return "register";
    }

    @GetMapping("/profile")
    public String profile(Principal principal, Model model) {
        LOG.log(Level.INFO, "Entering method: profile");
        String username = principal.getName();
        User user = userService.getUserByUsername(username);

        Profile userProfileView = new Profile(
                username,
                user.getFirstName(),
                user.getLastName(),
                user.getPassword(),
                user.getPhone_number()
        );
        model.addAttribute("offers",userService.allUserOffers(username));
        model.addAttribute("user", userProfileView);

        return "profile";
    }

}
