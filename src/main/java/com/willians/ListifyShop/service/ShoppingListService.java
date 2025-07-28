package com.willians.ListifyShop.service;

import com.willians.ListifyShop.dto.ShareListResponse;
import com.willians.ListifyShop.dto.SharedListRequest;
import com.willians.ListifyShop.dto.ShoppingListRequestDto;
import com.willians.ListifyShop.dto.ShoppingListResponseDto;
import com.willians.ListifyShop.entety.SharedList;
import com.willians.ListifyShop.entety.ShoppingList;
import com.willians.ListifyShop.entety.User;
import com.willians.ListifyShop.exception.NotFoundException;
import com.willians.ListifyShop.mapstruct.ShareListMapper;
import com.willians.ListifyShop.mapstruct.ShoppingListMapper;
import com.willians.ListifyShop.repository.ShareListRepository;
import com.willians.ListifyShop.repository.ShoppingListRepository;
import com.willians.ListifyShop.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
public class ShoppingListService {

    @Autowired
    private ShoppingListRepository shoppingListRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ShoppingListMapper shoppingListMapper;

    @Autowired
    private ShareListRepository shareListRepository;

    @Autowired
    private ShareListMapper shareListMapper;

    public ResponseEntity<List<ShoppingListResponseDto>> getAllShoppingLists(){
        List<ShoppingList> list = shoppingListRepository.findAll();
        List<ShoppingListResponseDto> listDto = shoppingListMapper.listShoppingListToResponse(list);
        return ResponseEntity.ok().body(listDto);
    }

    public ResponseEntity<ShoppingListResponseDto> getShoppingList(String shoppingListId){
        ShoppingList list = shoppingListRepository.findById(shoppingListId).orElseThrow(() -> new NotFoundException("Lista não encontrado."));
        ShoppingListResponseDto dto = shoppingListMapper.shoppingListToResponse(list);
        return ResponseEntity.ok().body(dto);

//        if (list.getOwner().getId().equals(user.getId())) {
//            return ResponseEntity.ok().body(dto);
//        }
//
//        if (list.getShared() != null){
//            if (list.getShared().getActiveSharing()){
//                return ResponseEntity.ok().body(dto);
//            }
//        }

//        throw new RuntimeException("Lista de compras não encotrada.");
    }

    public ResponseEntity<ShoppingListResponseDto> creatList(String userId, ShoppingListRequestDto shoppingListRequestDto) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("Usuário não encontrado."));
        ShoppingList list = shoppingListMapper.DtoToShoppigList(shoppingListRequestDto);
        list.setOwner(user);
        Instant date = Instant.now();
        list.setCreatAt(date);
        list.setUpdateAt(date);

        list = shoppingListRepository.save(list);

        ShoppingListResponseDto dto = shoppingListMapper.shoppingListToResponse(list);
        return ResponseEntity.ok().body(dto);
    }

    @Transactional
    public ResponseEntity<ShareListResponse> sharedList(String ownerId, String listId, SharedListRequest permission) {
        User owner = userRepository.findById(ownerId).orElseThrow(() -> new NotFoundException("Usuário não encontrado."));
        ShoppingList list = shoppingListRepository.findById(listId).orElseThrow(() -> new NotFoundException("Lista não encontrado."));

        String idShare = UUID.randomUUID().toString();
        String shareToken = "http://localhost:8081/shopping-lists/share-list/" + idShare;
        System.out.println("Passou aqui 1");

        SharedList sharedList = new SharedList(
                idShare,
                permission.permission(),
                shareToken,
                true,
                owner,
                list
                );

        System.out.println("Passou aqui 2");
        SharedList shared = shareListRepository.save(sharedList);
        ShareListResponse response = shareListMapper.shareToResponse(shared);

        return ResponseEntity.ok().body(response);
    }

    public ResponseEntity<ShoppingListResponseDto> updateList(String listId, ShoppingListRequestDto shoppingListRequestDto) {
        ShoppingList list = shoppingListRepository.findById(listId).orElseThrow(() -> new NotFoundException("Lista não encontrado."));

        list.setName(shoppingListRequestDto.name());
        Instant date = Instant.now();
        list.setUpdateAt(date);

        list = shoppingListRepository.save(list);

        ShoppingListResponseDto dto = shoppingListMapper.shoppingListToResponse(list);
        return ResponseEntity.ok().body(dto);
    }

    public ResponseEntity<Void> deleteList(String listId) {
        shoppingListRepository.deleteById(listId);
        return ResponseEntity.noContent().build();
    }

}
