package com.willians.ListifyShop.dto;

import com.willians.ListifyShop.entety.Role;

import java.util.List;

public record UserResponseDto(String id,
                              String name,
                              String cpf,
                              String email,
                              String urlImage,
                              List<Role> roles) {
}
