package com.willians.ListifyShop.dto;

import com.willians.ListifyShop.entety.Role;

import java.util.List;
import java.util.UUID;

public record UserResponseDto(UUID id,
                              String name,
                              String cpf,
                              String email,
                              String urlImage,
                              List<Role> roles) {
}
