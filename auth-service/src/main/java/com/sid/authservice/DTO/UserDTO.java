package com.sid.authservice.DTO;

import com.sid.authservice.entities.User;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserDTO {
    private  String id;
    private String username;
    private String email;

    public static UserDTO from(User user){
        return builder()
                .id(String.valueOf(user.getId()))
                .username(user.getUsername())
                .email(user.getEmail())
                .build();
    }
}
