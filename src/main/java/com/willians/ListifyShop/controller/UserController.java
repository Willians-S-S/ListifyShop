package com.willians.ListifyShop.controller;

import com.willians.ListifyShop.dto.UserRequestDto;
import com.willians.ListifyShop.dto.UserResponseDto;
import com.willians.ListifyShop.dto.UserUpdate;
import com.willians.ListifyShop.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN') or #id.equals(T(java.util.UUID).fromString(authentication.principal.claims['id']))")
    public ResponseEntity<UserResponseDto> findUserById(@PathVariable UUID id){
        return this.userService.findUserById(id);
    }

    @GetMapping("/email/{email}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN') or #email == authentication.name")
    public ResponseEntity<UserResponseDto> findUserByEmail(@PathVariable String email){
        return this.userService.findUserByEmail(email);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<List<UserResponseDto>> findAllUsers(){
        return this.userService.findAllUser();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN') or #id.equals(T(java.util.UUID).fromString(authentication.principal.claims['id']))")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable UUID id, @RequestBody UserUpdate userUpdate){
        return this.userService.updateUser(id, userUpdate);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN') or #id.equals(T(java.util.UUID).fromString(authentication.principal.claims['id']))")
    public ResponseEntity<String> deleteUser(@PathVariable UUID id){
        return this.userService.deleteUser(id);
    }
}
