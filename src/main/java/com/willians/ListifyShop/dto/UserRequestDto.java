package com.willians.ListifyShop.dto;


import com.willians.ListifyShop.validation.CpfValid;

public record UserRequestDto(String name, @CpfValid String cpf, String email, String password) {
}
