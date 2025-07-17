package com.willians.ListifyShop.controller;

import com.willians.ListifyShop.utils.ImageOperator;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/image")
public class ImageController {

    @GetMapping(value = "/{image}")
    public ResponseEntity<UrlResource> getImage(@PathVariable String image){
        ImageOperator imageOperator = new ImageOperator();
        return imageOperator.getImg(image);
    }
}
