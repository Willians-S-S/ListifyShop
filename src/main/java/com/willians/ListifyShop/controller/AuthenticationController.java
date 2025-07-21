package com.willians.ListifyShop.controller;

import com.willians.ListifyShop.dto.UserRequestDto;
import com.willians.ListifyShop.dto.UserResponseDto;
import com.willians.ListifyShop.security.authentication.AuthenticationService;
import com.willians.ListifyShop.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String authenticate(Authentication authentication){
        return authenticationService.autheticate(authentication);
    }


    @PostMapping(value = "/sign", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public UserResponseDto addUser(
            @Valid @RequestPart("user") UserRequestDto userRequest,
            @RequestPart(value = "image", required = false) MultipartFile image
    ){
        return this.userService.addUser(userRequest, image);
    }
}
