package com.willians.ListifyShop.dto;

import java.util.UUID;

public record UserRequestDto(String name, String cpf, String email, String password) {
}
