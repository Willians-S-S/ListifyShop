package com.willians.ListifyShop.dto;

import java.util.UUID;

public record UserUpdate(UUID id, String name, String cpf, String email, String password) {
}
