package com.willians.ListifyShop.utils;

import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
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
}
