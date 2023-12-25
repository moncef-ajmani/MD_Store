package com.sid.authservice.web;

import com.sid.authservice.DTO.UserDTO;
import com.sid.authservice.entities.User;
import com.sid.authservice.repository.UserRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/{id}")
    public ResponseEntity user(@PathVariable Long id){
        System.out.println(id);
        return ResponseEntity.ok(UserDTO.from(userRepository.findUserById(id)));
    }

}
