package com.willians.ListifyShop.service;

import com.willians.ListifyShop.dto.UserRequestDto;
import com.willians.ListifyShop.dto.UserResponseDto;
import com.willians.ListifyShop.entety.User;
import com.willians.ListifyShop.mapstruct.UserMapper;
import com.willians.ListifyShop.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTeste {

    @InjectMocks
    UserService userService;

    @Mock
    UserRepository userRepository;
    @Mock
    UserMapper userMapper;

    User user;
    UserRequestDto userRequest;
    UserResponseDto userResponse;

    @BeforeEach
    void setUp(){
        userRequest = new UserRequestDto("nome", "cpf", "email", "password");
        user = new User();
        userResponse = new UserResponseDto(UUID.randomUUID(), "nome", "cpf", "email");

        when(userMapper.requestToUser(userRequest)).thenReturn(user);
        when(userMapper.userToResponse(user)).thenReturn(userResponse);
    }

    @Test
    void deveAdicionarUsuarioQuandoDadosValidos(){
        Mockito.when(userRepository.save(user)).thenReturn(user);

        UserResponseDto userReturn = userService.addUser(userRequest);

        Assertions.assertEquals(userResponse, userReturn);
        verify(userRepository).save(user);
    }


}
