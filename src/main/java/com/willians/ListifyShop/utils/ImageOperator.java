package com.willians.ListifyShop.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

public class ImageOperator {
    private final String path = "/home/will/Documentos/Projetos/ListifyShop/src/main/resources/images";


     public String saveImg(MultipartFile image){
        String fileName = UUID.randomUUID() + image.getOriginalFilename();
        Path uploadPath = Paths.get(path);
        Path saveLocal = uploadPath.resolve(fileName);

        try {
            image.transferTo(saveLocal);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return fileName;
    }
}
