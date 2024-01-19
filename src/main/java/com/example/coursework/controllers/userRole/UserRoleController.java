package com.example.coursework.controllers.userRole;//package com.example.carsale.controllers.userRole;
//
//import com.example.carsale.dto.UserRoleDTO;
//import com.example.carsale.service.UserRoleService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.UUID;
//
//@RestController
//@RequestMapping("/userRoles")
//public class UserRoleController {
//    private final UserRoleService userRoleService;
//
//    @Autowired
//    public UserRoleController(UserRoleService userRoleService) {
//        this.userRoleService = userRoleService;
//    }
//
//    @PostMapping ("/create")
//    public UserRoleDTO createUserRole(@RequestBody UserRoleDTO userRoleDTO) {
//        return userRoleService.createUserRole(userRoleDTO);
//    }
//
//    @GetMapping("/alluserrole")
//    public List<UserRoleDTO> getAllUserRoles() {
//        return userRoleService.getAllUserRoles();
//    }
//
//    @GetMapping("/getuserRole/{id}")
//    public UserRoleDTO getUserRoleByID(@PathVariable UUID id) {
//        return userRoleService.getUserRoleByID(id).orElse(null);
//    }
//
//    @DeleteMapping("/{id}")
//    public void deleteUserRole(@PathVariable UUID id) {
//        userRoleService.deleteUserRole(id);
//    }
//    @GetMapping("/active-user-role")
//    public List<Object[]> getActiveUsersWithRoles() {
//        return userRoleService.getActiveUsersWithRoles();
//    }
//}
//
