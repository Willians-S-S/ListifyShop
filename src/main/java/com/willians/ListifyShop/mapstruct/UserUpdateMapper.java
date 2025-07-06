package com.willians.ListifyShop.mapstruct;

import com.willians.ListifyShop.dto.UserUpdate;
import com.willians.ListifyShop.entety.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserUpdateMapper {

    void userUpdateToUser(UserUpdate userUpdate, @MappingTarget User user);
}
