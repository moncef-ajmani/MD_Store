package com.sid.authservice.security;

import com.sid.authservice.entities.User;
import com.sid.authservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;
import org.springframework.core.convert.converter.Converter;
import java.util.Collections;

@Component
public class JwtToUserConverter implements Converter<Jwt, UsernamePasswordAuthenticationToken> {
    @Autowired
    UserRepository userRepository;
    @Override
    public UsernamePasswordAuthenticationToken convert(Jwt jwt){
        User user = userRepository.findUserById(Long.valueOf(jwt.getSubject()));

        return new UsernamePasswordAuthenticationToken(user,jwt,user.getAuthorities());
    }
}
