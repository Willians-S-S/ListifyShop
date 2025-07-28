package com.willians.ListifyShop.controller;

import com.willians.ListifyShop.dto.ShoppingListRequestDto;
import com.willians.ListifyShop.dto.ShoppingListResponseDto;
import com.willians.ListifyShop.service.ShoppingListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shopping-lists")
public class ShoppingListController {

    @Autowired
    private ShoppingListService shoppingListService;

    @GetMapping(value = "/all")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<List<ShoppingListResponseDto>> getAllShoppingLists(){
        return shoppingListService.getAllShoppingLists();
    }

    @GetMapping
    @PreAuthorize("hasAuthority('SCOPE_ADMIN') or @authorizationService.hasOwnershipOrSharedAccess(authentication, #userId, #listId)")
    public ResponseEntity<ShoppingListResponseDto> getShoppingList(
            @RequestParam String userId, @RequestParam(name = "listId") String listId){
            return shoppingListService.getShoppingList(listId);
    }

    @PostMapping(value = "/{userId}")
    @PreAuthorize("#userId.equals(authentication.principal.claims['id'])")
    public ResponseEntity<ShoppingListResponseDto> creatList(@PathVariable String userId, @RequestBody ShoppingListRequestDto shoppingListRequestDto){
        return shoppingListService.creatList(userId, shoppingListRequestDto);
    }

    @PutMapping
    @PreAuthorize("@authorizationService.isUserAndListOwnerMatch(authentication, #userId, #listId)")
    public ResponseEntity<ShoppingListResponseDto> updateList(
            @RequestParam String userId,
            @RequestParam(name = "listId") String listId,
            @RequestBody ShoppingListRequestDto shoppingListRequestDto){
        return shoppingListService.updateList(listId, shoppingListRequestDto);
    }

    @DeleteMapping
    @PreAuthorize("@authorizationService.isUserAndListOwnerMatch(authentication, #userId, #listId)")
    public ResponseEntity<Void> deleteList(
            @RequestParam String userId,
            @RequestParam(name = "listId") String listId){
        return shoppingListService.deleteList(listId);
    }
}
