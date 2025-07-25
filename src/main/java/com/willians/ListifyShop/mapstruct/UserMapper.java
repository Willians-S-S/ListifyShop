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
    @Mapping(source = "urlImage", target = "urlImage")
    UserResponseDto userToResponse(User user);
    List<UserResponseDto> listUserToResponse(List<User> listUser);

}
