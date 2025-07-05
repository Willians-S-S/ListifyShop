package com.willians.ListifyShop.controller;

import com.willians.ListifyShop.dto.UserRequestDto;
import com.willians.ListifyShop.dto.UserResponseDto;
import com.willians.ListifyShop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public UserResponseDto addUser(@RequestBody UserRequestDto userRequest){
        return this.userService.addUser(userRequest);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> findUserById(@PathVariable UUID id){
        return this.userService.findUserById(id);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UserResponseDto> findUserByEmail(@PathVariable String email){
        return this.userService.findUserByEmail(email);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> findAllUsers(){
        return this.userService.findAllUser();
    }
}
