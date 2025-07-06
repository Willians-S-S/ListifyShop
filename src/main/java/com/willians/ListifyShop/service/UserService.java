package com.willians.ListifyShop.service;

import com.willians.ListifyShop.dto.UserRequestDto;
import com.willians.ListifyShop.dto.UserResponseDto;
import com.willians.ListifyShop.dto.UserUpdate;
import com.willians.ListifyShop.entety.User;
import com.willians.ListifyShop.mapstruct.UserMapper;
import com.willians.ListifyShop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper mapper;

    public UserResponseDto addUser(UserRequestDto userRequest){
        this.userRepository.findByEmail(userRequest.email()).ifPresent(e -> {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email já utilizado.");
        });

        this.userRepository.findByCpf(userRequest.cpf()).ifPresent(e -> {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Cpf já utilizado.");
        });

        User user = mapper.requestToUser(userRequest);
        user = this.userRepository.save(user);
        return mapper.userToResponse(user);
    }

    public ResponseEntity<UserResponseDto> findUserById(UUID id){
        User user = this.userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado."));

        return ResponseEntity.ok(mapper.userToResponse(user));
    }

    public ResponseEntity<UserResponseDto> findUserByEmail(String email){
        User user = this.userRepository.findByEmail(email).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado."));

        return ResponseEntity.ok(mapper.userToResponse(user));
    }

    public ResponseEntity<List<UserResponseDto>> findAllUser(){
        List<User> users = this.userRepository.findAll();
        List<UserResponseDto> usersResponse = mapper.listUserToResponse(users);
        return ResponseEntity.ok(usersResponse);
    }

    public ResponseEntity<UserResponseDto> updateUser(UserUpdate userUpdate){
        User user = this.userRepository.findById(userUpdate.id()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado."));

        user.setCpf(userUpdate.cpf());
        user.setEmail(userUpdate.email());
        user.setName(userUpdate.name());
        user.setPassword(userUpdate.password());

        this.userRepository.save(user);

        return  ResponseEntity.ok(mapper.userToResponse(user));
    }

    public ResponseEntity<String> deleteUser(UUID id){
        User user = this.userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado."));
        this.userRepository.delete(user);

        return ResponseEntity.ok("Usuário deletado com sucesso.");
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
