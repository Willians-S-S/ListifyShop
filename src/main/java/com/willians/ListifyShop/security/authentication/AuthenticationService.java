package com.willians.ListifyShop.security.authentication;

import com.willians.ListifyShop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    JwtService jwtService;

    public AuthenticationService(JwtService jwtService){
        this.jwtService = jwtService;
    }

    public String authenticate(Authentication authentication){
        return jwtService.genereteToken(authentication);
    }
}
