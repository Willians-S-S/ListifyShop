package com.willians.ListifyShop.utils;

import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

public class ImageOperator {
    private final String path = "/home/will/Documentos/Projetos/ListifyShop/src/main/resources/images";


     public String saveImg(MultipartFile image){
         try {
             String fileName = UUID.randomUUID() + image.getOriginalFilename();
             Path uploadPath = Paths.get(path);
             Path saveLocal = uploadPath.resolve(fileName);

             image.transferTo(saveLocal);
             return fileName;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ResponseEntity<UrlResource> getImg(String nameImg){
        try {
            Path img = Paths.get(this.path).resolve(nameImg);
            UrlResource resource = new UrlResource(img.toUri());
            if(resource.exists()){
                return ResponseEntity
                        .ok()
                        .contentType(MediaType.IMAGE_PNG)
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public ResponseEntity<Void> deleteImg(String nameImg){
         try {
             Path imgPath = Paths.get(this.path).resolve(nameImg);
             Files.deleteIfExists(imgPath);

             return  ResponseEntity.ok().build();
         } catch (IOException e) {
             throw new RuntimeException(e);
         }
    }
}
