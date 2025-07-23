package com.willians.ListifyShop.controller;

import com.willians.ListifyShop.dto.UserResponseDto;
import com.willians.ListifyShop.service.ImageService;
import com.willians.ListifyShop.utils.ImageOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping("/image")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @PostMapping(value = "/{userId}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN') or #userId.equals(authentication.principal.claims['id'])")
    public ResponseEntity<UserResponseDto> saveImgUser(@PathVariable String userId, @RequestPart("image") MultipartFile image){
        return imageService.saveImgUser(userId, image);
    }

    @GetMapping(value = "{userId}/{image}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN') or #userId.equals(authentication.principal.claims['id'])")
    public ResponseEntity<UrlResource> getImage(@PathVariable String userId, @PathVariable String image){
        ImageOperator imageOperator = new ImageOperator();
        return imageOperator.getImg(image);
    }

    @DeleteMapping(value = "{userId}/{image}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN') or #userId.equals(authentication.principal.claims['id'])")
    public ResponseEntity<Void> deleteImg(@PathVariable String userId, @PathVariable String image){

        return imageService.deleteImg(userId, image);
    }
}
