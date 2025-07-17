package com.willians.ListifyShop.service;

import com.willians.ListifyShop.dto.UserRequestDto;
import com.willians.ListifyShop.dto.UserResponseDto;
import com.willians.ListifyShop.dto.UserUpdate;
import com.willians.ListifyShop.entety.User;
import com.willians.ListifyShop.exception.NotFoundException;
import com.willians.ListifyShop.mapstruct.UserMapper;
import com.willians.ListifyShop.mapstruct.UserUpdateMapper;
import com.willians.ListifyShop.repository.UserRepository;
import com.willians.ListifyShop.utils.ImageOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper mapper;

    @Autowired
    private UserUpdateMapper userUpdateMapper;

    public UserResponseDto addUser(UserRequestDto userRequest, MultipartFile image){
        this.userRepository.findByEmail(userRequest.email()).ifPresent(e -> {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email já utilizado.");
        });

        this.userRepository.findByCpf(userRequest.cpf()).ifPresent(e -> {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Cpf já utilizado.");
        });

        User user = mapper.requestToUser(userRequest);

        if(image != null){
            ImageOperator imageOperator = new ImageOperator();
            String nameImg = imageOperator.saveImg(image);
            user.setUrlImage(nameImg);
        }
        user = this.userRepository.save(user);
        return mapper.userToResponse(user);
    }

    public ResponseEntity<UserResponseDto> findUserById(UUID id){
        User user = this.userRepository.findById(id).orElseThrow(() -> new NotFoundException("Usuário não encontrado."));

        return ResponseEntity.ok(mapper.userToResponse(user));
    }

    public ResponseEntity<UserResponseDto> findUserByEmail(String email){
        User user = this.userRepository.findByEmail(email).orElseThrow(()-> new NotFoundException("Usuário não encontrado."));

        return ResponseEntity.ok(mapper.userToResponse(user));
    }

    public ResponseEntity<List<UserResponseDto>> findAllUser(){
        List<User> users = this.userRepository.findAll();
        List<UserResponseDto> usersResponse = mapper.listUserToResponse(users);
        return ResponseEntity.ok(usersResponse);
    }

    public ResponseEntity<UserResponseDto> updateUser(UserUpdate userUpdate){
        User user = this.userRepository.findById(userUpdate.id()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado."));

        userUpdateMapper.userUpdateToUser(userUpdate, user);
        this.userRepository.save(user);

        return  ResponseEntity.ok(mapper.userToResponse(user));
    }

    public ResponseEntity<String> deleteUser(UUID id){
        User user = this.userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado."));
        this.userRepository.delete(user);

        return ResponseEntity.ok("Usuário deletado com sucesso.");
    }
}
