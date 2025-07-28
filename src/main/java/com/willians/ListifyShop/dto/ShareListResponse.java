package com.willians.ListifyShop.dto;

import com.willians.ListifyShop.entety.User;
import com.willians.ListifyShop.enums.PermissionLevel;

public record ShareListResponse (String id, String shareToken, PermissionLevel permission, UserResponseShareList owner){}
