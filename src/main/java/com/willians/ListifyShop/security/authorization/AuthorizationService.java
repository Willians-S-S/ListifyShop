package com.willians.ListifyShop.security.authorization;

import com.willians.ListifyShop.entety.SharedList;
import com.willians.ListifyShop.entety.ShoppingList;
import com.willians.ListifyShop.exception.NotFoundException;
import com.willians.ListifyShop.repository.ShareListRepository;
import com.willians.ListifyShop.repository.ShoppingListRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

@Component("authorizationService")
public class AuthorizationService {

    ShoppingListRepository shoppingListRepository;
    ShareListRepository shareListRepository;

    public AuthorizationService(ShoppingListRepository shoppingListRepository, ShareListRepository shareListRepository) {
        this.shoppingListRepository = shoppingListRepository;
        this.shareListRepository = shareListRepository;
    }

    public boolean isSelf(Authentication authentication, String idUser){
        try {
            Jwt jwt = (Jwt) authentication.getPrincipal();
            String idToken = jwt.getClaims().get("id").toString();
            return idUser.equals(idToken);
        } catch (RuntimeException e) {
            throw new RuntimeException("509: Erro interno");
        }
    }

    public boolean hasOwnershipOrSharedAccess(Authentication authentication, String idUser, String idList){
            if (!isSelf(authentication, idUser)){
                return false;
            }
            System.out.println();
            ShoppingList shoppingList = shoppingListRepository.findById(idList).orElseThrow(() -> new NotFoundException("Lista não encontrada."));

            if (shoppingList.getOwner().getId().equals(idUser)){
                return true;
            }

            if (shoppingList.getShared() != null){
                return shoppingList.getShared().getActiveSharing();
            }

            return false;
    }

    public boolean isUserAndListOwnerMatch(Authentication authentication, String idOwner, String idList){

        if (!isSelf(authentication, idOwner)){
            return false;
        }

        ShoppingList shoppingList = shoppingListRepository.findById(idList).orElseThrow(() -> new NotFoundException("Lista não encontrada."));

        if (shoppingList.getOwner().getId().equals(idOwner)){
            return true;
        }

        return false;
    }

    public boolean isUserAndListShareOwnerMatch(Authentication authentication, String idOwner, String idShareList){

        if (!isSelf(authentication, idOwner)){
            return false;
        }

        SharedList shareList = shareListRepository.findById(idShareList).orElseThrow(() -> new NotFoundException("Lista não encontrada."));

        if (shareList.getOwner().getId().equals(idOwner)){
            return true;
        }

        return false;
    }
}
