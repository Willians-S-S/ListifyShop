package com.willians.ListifyShop.controller;

import com.willians.ListifyShop.dto.ShareListResponse;
import com.willians.ListifyShop.dto.SharedListRequest;
import com.willians.ListifyShop.service.SharedListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/share-list")
public class SharedListController {
    @Autowired
    private SharedListService sharedListService;

    // TODO: Criar um entity de listas compartilhadas comigo

    @GetMapping
    @PreAuthorize("@authorizationService.isUserAndListShareOwnerMatch(authentication, #ownerId, #listShareId)")
    public ResponseEntity<ShareListResponse> getSharedList(
            @RequestParam String ownerId,
            @RequestParam(name = "listShareId") String listShareId
    ){
        return sharedListService.getSharedList(listShareId);
    }

    @PostMapping
    @PreAuthorize("@authorizationService.isUserAndListOwnerMatch(authentication, #ownerId, #listId)")
    public ResponseEntity<ShareListResponse> sharedList(
            @RequestParam String ownerId,
            @RequestParam(name = "listId") String listId,
            @RequestBody SharedListRequest permission
    ){
        return sharedListService.sharedList(ownerId, listId, permission);
    }

    @PutMapping
    @PreAuthorize("@authorizationService.isUserAndListShareOwnerMatch(authentication, #ownerId, #listShareId)")
    public ResponseEntity<ShareListResponse> sharedListUpdate(
            @RequestParam String ownerId,
            @RequestParam(name = "listShareId") String listShareId,
            @RequestBody SharedListRequest permission
    ){
        return sharedListService.sharedListUpdate(listShareId, permission);
    }

    @DeleteMapping
    @PreAuthorize("@authorizationService.isUserAndListShareOwnerMatch(authentication, #ownerId, #listShareId)")
    public ResponseEntity<Void> sharedListDelete(
            @RequestParam String ownerId,
            @RequestParam(name = "listShareId") String listShareId
    ){
        return sharedListService.sharedListDelete(listShareId);
    }

}
