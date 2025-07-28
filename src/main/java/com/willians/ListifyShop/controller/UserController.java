package com.willians.ListifyShop.controller;

import com.willians.ListifyShop.dto.UserResponseDto;
import com.willians.ListifyShop.dto.UserUpdate;
import com.willians.ListifyShop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
//    @PreAuthorize("hasAuthority('SCOPE_ADMIN') or #id.equals(authentication.principal.claims['id'])")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN') or @authorizationService.isSelf(authentication, #id)")
    public ResponseEntity<UserResponseDto> findUserById(@PathVariable String id, JwtAuthenticationToken token){
        System.out.println(token.getTokenAttributes());
        System.out.println(token.getToken());
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
    @PreAuthorize("hasAuthority('SCOPE_ADMIN') or #id.equals(authentication.principal.claims['id'])")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable String id, @RequestBody UserUpdate userUpdate){
        return this.userService.updateUser(id, userUpdate);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN') or #id.equals(authentication.principal.claims['id'])")
    public ResponseEntity<String> deleteUser(@PathVariable String id){
        return this.userService.deleteUser(id);
    }
}
