package com.willians.ListifyShop.controller;

import com.willians.ListifyShop.dto.UserRequestDto;
import com.willians.ListifyShop.dto.UserResponseDto;
import com.willians.ListifyShop.dto.UserUpdate;
import com.willians.ListifyShop.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public UserResponseDto addUser(
            @Valid @RequestPart("user") UserRequestDto userRequest,
            @RequestPart(value = "image", required = false) MultipartFile image
    ){
        return this.userService.addUser(userRequest, image);
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

    @PutMapping
    public ResponseEntity<UserResponseDto> updateUser(@RequestBody UserUpdate userUpdate){
        return this.userService.updateUser(userUpdate);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable UUID id){
        return this.userService.deleteUser(id);
    }
}
