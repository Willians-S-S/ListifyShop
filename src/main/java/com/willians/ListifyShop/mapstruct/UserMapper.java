package com.willians.ListifyShop.mapstruct;

import com.willians.ListifyShop.dto.UserRequestDto;
import com.willians.ListifyShop.dto.UserResponseDto;
import com.willians.ListifyShop.entety.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "id", ignore = true)
    User requestToUser(UserRequestDto request);
    UserResponseDto userToResponse(User user);
    List<User> listUserToResponse(List<User> listUser);
}
