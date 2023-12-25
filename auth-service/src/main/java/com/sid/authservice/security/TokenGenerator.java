package com.sid.authservice.security;

import com.sid.authservice.DTO.TokenDTO;
import com.sid.authservice.entities.User;
import net.bytebuddy.utility.dispatcher.JavaDispatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

@Component
public class TokenGenerator {
    @Autowired
    JwtEncoder accessTokenEncoder;
    @Autowired
    @Qualifier("jwtRefreshTokenEncoder")
    JwtEncoder refreshTokenEncoder;

    private String createAccessToken(Authentication authentication){
        User user = (User) authentication.getPrincipal();
        Instant now = Instant.now();
        System.out.println(user.getAuthorities());
        JwtClaimsSet claimsSet = JwtClaimsSet.builder()
                .issuer("Portfolio_CMS_Platform")
                .issuedAt(now)
                .expiresAt(now.plus(5, ChronoUnit.MINUTES))
                .subject(user.getId().toString())
                .claim("roles",user.getAuthorities()
                        .stream()
                        .map(authority ->((SimpleGrantedAuthority) authority).getAuthority()) // Extract role name
                        .collect(Collectors.toList()))
                .build();
        return accessTokenEncoder.encode(JwtEncoderParameters.from(claimsSet)).getTokenValue();
    }
    private String createRefreshToken(Authentication authentication){
        User user = (User) authentication.getPrincipal();
        Instant now = Instant.now();

        JwtClaimsSet claimsSet = JwtClaimsSet.builder()
                .issuer("Portfolio_CMS_Platform")
                .issuedAt(now)
                .expiresAt(now.plus(30, ChronoUnit.DAYS))
                .subject(user.getId().toString())
                .claim("roles",user.getAuthorities().stream().map(ga->ga.getAuthority()).collect(Collectors.toList()))
                .build();
        return refreshTokenEncoder.encode(JwtEncoderParameters.from(claimsSet)).getTokenValue();
    }

    public TokenDTO createToken(Authentication authentication){
        if(!(authentication.getPrincipal() instanceof User user)){
            throw new BadCredentialsException(
                    MessageFormat.format("principal {0} is not of User type",authentication.getPrincipal().getClass())
            ) ;
        }
        TokenDTO tokenDTO = new TokenDTO();
        tokenDTO.setUserId(user.getId());
        tokenDTO.setAccessToken(createAccessToken(authentication));

        String refreshToken;
        if(authentication.getCredentials() instanceof Jwt jwt){
            Instant now = Instant.now();
            Instant expiresAt = jwt.getExpiresAt();
            Duration duration = Duration.between(now,expiresAt);
            long daysUntilExpired = duration.toDays();
            if(daysUntilExpired < 7){
                refreshToken = createRefreshToken(authentication);
            }else{
                refreshToken = jwt.getTokenValue();
            }
        }else{
            refreshToken = createRefreshToken(authentication);
        }
        tokenDTO.setRefreshToken(refreshToken);
        return tokenDTO;
    }
}
