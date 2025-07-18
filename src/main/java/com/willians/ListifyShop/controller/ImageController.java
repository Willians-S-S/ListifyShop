package com.willians.ListifyShop.controller;

import com.willians.ListifyShop.dto.UserResponseDto;
import com.willians.ListifyShop.service.ImageService;
import com.willians.ListifyShop.utils.ImageOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping("/image")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @PostMapping(value = "/{idUser}")
    public ResponseEntity<UserResponseDto> saveImgUser(@PathVariable UUID idUser, @RequestPart("image") MultipartFile image){
        return imageService.saveImgUser(idUser, image);
    }

    @GetMapping(value = "/{image}")
    public ResponseEntity<UrlResource> getImage(@PathVariable String image){
        ImageOperator imageOperator = new ImageOperator();
        return imageOperator.getImg(image);
    }

    @DeleteMapping(value = "/{image}")
    public ResponseEntity<Void> deleteImg(@PathVariable String image){
        ImageOperator imageOperator = new ImageOperator();
        return imageOperator.deleteImg(image);
    }
}
