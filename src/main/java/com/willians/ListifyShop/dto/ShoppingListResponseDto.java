package com.willians.ListifyShop.dto;

import java.time.Instant;

public record ShoppingListResponseDto(
        String id,
        String name,
        Instant creatAt,
        Instant updateAt,
        UserResponseDto owner) {}
