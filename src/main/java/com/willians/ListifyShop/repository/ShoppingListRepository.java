package com.willians.ListifyShop.repository;

import com.willians.ListifyShop.entety.ShoppingList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShoppingListRepository extends JpaRepository<ShoppingList, String> {
    Optional<ShoppingList> findById(String id);
}
