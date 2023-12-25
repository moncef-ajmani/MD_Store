package com.sid.authservice.web;

import com.sid.authservice.DTO.RoleDTO;
import com.sid.authservice.DTO.RoleUserDTO;
import com.sid.authservice.DTO.SignupDTO;
import com.sid.authservice.entities.Role;
import com.sid.authservice.entities.User;
import com.sid.authservice.repository.RoleRepository;
import com.sid.authservice.repository.UserRepository;
import com.sid.authservice.service.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    UserManager userManager;

    @GetMapping("/users")
    public ResponseEntity<List<User>> listUsers() {
        List<User> users = userRepository.findAll();
        return ResponseEntity.ok(users);
    }

    @PostMapping("/users")
    public ResponseEntity<User> saveUser(@RequestBody SignupDTO signupDTO){

        User user = new User(signupDTO.getUsername(), signupDTO.getPassword(), signupDTO.getEmail());

        // Check if the user already exists before creating
        if (userRepository.existsByEmail(user.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }

        userManager.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
    @GetMapping("/roles")
    public ResponseEntity<List<Role>> listRoles() {
        List<Role> roles = roleRepository.findAll();
        return ResponseEntity.ok(roles);
    }
    @PostMapping("/roles")
    public ResponseEntity<Role> saveRole(@RequestBody RoleDTO roleDTO) {
        Role role = new Role(null, roleDTO.getName());
        userManager.addNewRole(role);
        return ResponseEntity.status(HttpStatus.CREATED).body(role);
    }

    @PostMapping( "/addRoleToUser")
    public ResponseEntity addRoleToUser(@RequestBody RoleUserDTO roleUserDTO){

        User user = userRepository.findUserById(roleUserDTO.getUserId());
        Role role = roleRepository.findRoleById(roleUserDTO.getRoleId());

        if (user == null || role == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User or Role not found");
        }

        if (user.getRoles().contains(role)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User already has the role");
        }

        user.getRoles().add(role);
        userRepository.save(user);

        return ResponseEntity.ok("Role added to user: " + user.getUsername());
    }
}
