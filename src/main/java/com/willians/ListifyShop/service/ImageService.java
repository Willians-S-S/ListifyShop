package com.willians.ListifyShop.service;

import com.willians.ListifyShop.dto.UserResponseDto;
import com.willians.ListifyShop.entety.User;
import com.willians.ListifyShop.exception.NotFoundException;
import com.willians.ListifyShop.mapstruct.UserMapper;
import com.willians.ListifyShop.repository.UserRepository;
import com.willians.ListifyShop.utils.ImageOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
public class ImageService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;
    private ImageOperator imageOperator = new ImageOperator();

    public ResponseEntity<UserResponseDto> saveImgUser(String idUser, MultipartFile image){
        User user = userRepository.findById(idUser)
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado."));

        if (image.isEmpty()) {
            throw new RuntimeException("Erro! Arquivo vázio.");
        }
        System.out.println("Passou aqui");
        String nameImg = imageOperator.saveImg(image);
        user.setUrlImage(nameImg);

        user = userRepository.save(user);
        return ResponseEntity.ok().body(userMapper.userToResponse(user));
    }
}
