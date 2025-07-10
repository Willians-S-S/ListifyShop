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
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
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

        lenient().when(userMapper.requestToUser(userRequest)).thenReturn(user);
        lenient().when(userMapper.userToResponse(user)).thenReturn(userResponse);
    }

    @Test
    void deveAdicionarUsuarioQuandoDadosValidos(){
        Mockito.when(userRepository.save(user)).thenReturn(user);

        UserResponseDto userReturn = userService.addUser(userRequest);

        Assertions.assertEquals(userResponse, userReturn);
        verify(userRepository).save(user);
    }

    @Test
    void naoDeveAdicionarUsuarioQuandoEmailJaExiste(){
        // 1. Arrange: Mock do repositório para retornar um usuário existente
        Mockito.when(userRepository.findByEmail(userRequest.email())).thenReturn(Optional.of(user));

        // 2. Act & Assert: Chamar o método do serviço e verificar a exceção
        ResponseStatusException e = Assertions.assertThrows(ResponseStatusException.class, () -> {
            userService.addUser(userRequest);
        });

        // 3. Assert: Verificar a mensagem da exceção usando AssertJ
        assertThat(e.getMessage()).isEqualTo("409 CONFLICT \"Email já utilizado.\"");

        // Verificar que 'save' NUNCA foi chamado
        Mockito.verify(userRepository, Mockito.never()).save(Mockito.any(User.class));
        // Verificar que findByEmail foi chamado
        Mockito.verify(userRepository).findByEmail(userRequest.email());
    }


}