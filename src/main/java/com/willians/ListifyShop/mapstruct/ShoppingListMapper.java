package com.willians.ListifyShop.mapstruct;

import com.willians.ListifyShop.dto.ShoppingListRequestDto;
import com.willians.ListifyShop.dto.ShoppingListResponseDto;
import com.willians.ListifyShop.entety.ShoppingList;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ShoppingListMapper {
    ShoppingList DtoToShoppigList(ShoppingListRequestDto dto);
    ShoppingListResponseDto shoppingListToResponse(ShoppingList list);
    List<ShoppingListResponseDto> listShoppingListToResponse(List<ShoppingList> list);
}
