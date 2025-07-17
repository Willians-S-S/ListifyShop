package com.willians.ListifyShop.controller;

import com.willians.ListifyShop.utils.ImageOperator;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/image")
public class ImageController {

    @RequestMapping(value = "/{image}")
    public ResponseEntity<UrlResource> getImage(@PathVariable String image){
        ImageOperator imageOperator = new ImageOperator();
        return imageOperator.getImg(image);
    }
}
