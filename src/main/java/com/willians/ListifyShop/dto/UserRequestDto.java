package com.willians.ListifyShop.dto;

import java.util.UUID;

public record UserRequestDto(UUID id, String name, String cpf, String email) {
}
