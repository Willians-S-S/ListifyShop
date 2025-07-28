package com.willians.ListifyShop.service;

import com.willians.ListifyShop.dto.ShareListResponse;
import com.willians.ListifyShop.dto.SharedListRequest;
import com.willians.ListifyShop.entety.SharedList;
import com.willians.ListifyShop.entety.ShoppingList;
import com.willians.ListifyShop.entety.User;
import com.willians.ListifyShop.exception.NotFoundException;
import com.willians.ListifyShop.mapstruct.ShareListMapper;
import com.willians.ListifyShop.repository.ShareListRepository;
import com.willians.ListifyShop.repository.ShoppingListRepository;
import com.willians.ListifyShop.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SharedListService {
    @Autowired
    private ShoppingListRepository shoppingListRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ShareListRepository shareListRepository;

    @Autowired
    private ShareListMapper shareListMapper;

    public ResponseEntity<ShareListResponse> getSharedList(String listId) {
        SharedList sharedList = shareListRepository.findById(listId).orElseThrow(() -> new  NotFoundException("Lista não encontrado."));

        ShareListResponse response = shareListMapper.shareToResponse(sharedList);

        return ResponseEntity.ok().body(response);
    }

    @Transactional
    public ResponseEntity<ShareListResponse> sharedList(String ownerId, String listId, SharedListRequest permission) {
        User owner = userRepository.findById(ownerId).orElseThrow(() -> new NotFoundException("Usuário não encontrado."));
        ShoppingList list = shoppingListRepository.findById(listId).orElseThrow(() -> new NotFoundException("Lista não encontrado."));

        String idShare = UUID.randomUUID().toString();
        String shareToken = STR."http://localhost:8081/shopping-lists/share-list/\{list.getId()}";

        SharedList sharedList = new SharedList(
                idShare,
                permission.permission(),
                shareToken,
                true,
                owner,
                list
        );

        SharedList shared = shareListRepository.save(sharedList);
        ShareListResponse response = shareListMapper.shareToResponse(shared);

        return ResponseEntity.ok().body(response);
    }

    @Transactional
    public ResponseEntity<ShareListResponse> sharedListUpdate(String listId, SharedListRequest permission) {
        SharedList sharedList = shareListRepository.findById(listId).orElseThrow(() -> new  NotFoundException("Lista não encontrado."));

        sharedList.setPermission(permission.permission());

        SharedList shared = shareListRepository.save(sharedList);
        ShareListResponse response = shareListMapper.shareToResponse(shared);

        return ResponseEntity.ok().body(response);
    }

    public ResponseEntity<Void> sharedListDelete(String listId) {
        SharedList sharedList = shareListRepository.findById(listId).orElseThrow(() -> new  NotFoundException("Lista não encontrado."));

        shareListRepository.delete(sharedList);
        return ResponseEntity.noContent().build();
    }
}
