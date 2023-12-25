package com.sid.authservice.DTO;

import lombok.Data;

@Data
public class SignupDTO {
    private String username;
    private String password;
    private String email;
}
