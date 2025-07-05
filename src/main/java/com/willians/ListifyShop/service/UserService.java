package com.willians.ListifyShop.service;

import com.willians.ListifyShop.dto.UserRequestDto;
import com.willians.ListifyShop.dto.UserResponseDto;
import com.willians.ListifyShop.entety.User;
import com.willians.ListifyShop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Consumer;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserResponseDto addUser(UserRequestDto userRequest){
        this.userRepository.findByEmail(userRequest.email()).ifPresent(e -> {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email já utilizado!");
        });

        this.userRepository.findByCpf(userRequest.cpf()).ifPresent(e -> {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Cpf já utilizado.");
        });

        User user = convertRequestDtoToUser(userRequest);
        user = this.userRepository.save(user);
        return convertUserToResponseDto(user);
    }

    public ResponseEntity<UserResponseDto> findUserById(UUID id){
        User user = this.userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));

        return ResponseEntity.ok(convertUserToResponseDto(user));
    }

    public ResponseEntity<UserResponseDto> findUserByEmail(String email){
        User user = this.userRepository.findByEmail(email).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));

        return ResponseEntity.ok(convertUserToResponseDto(user));
    }

    public ResponseEntity<List<UserResponseDto>> findAllUser(){
        List<User> users = this.userRepository.findAll();
        List<UserResponseDto> usersResponse = users.stream().map(this::convertUserToResponseDto).toList();
        return ResponseEntity.ok(usersResponse);
    }

    public User convertRequestDtoToUser(UserRequestDto userResquest){
        return new User(
                userResquest.name(),
                userResquest.cpf(),
                userResquest.email(),
                userResquest.password());
    }

    public UserResponseDto convertUserToResponseDto(User user){
        return new UserResponseDto(
                user.getId(),
                user.getName(),
                user.getCpf(),
                user.getEmail());
    }
}
