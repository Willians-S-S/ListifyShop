package com.willians.ListifyShop.dto;

import java.util.UUID;

public record UserResponseDto(UUID id, String name, String cpf, String email, String urlImage) {
}
