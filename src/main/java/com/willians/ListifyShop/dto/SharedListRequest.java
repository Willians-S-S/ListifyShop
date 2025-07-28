package com.willians.ListifyShop.dto;

import com.willians.ListifyShop.enums.PermissionLevel;

public record SharedListRequest(PermissionLevel permission) { }
