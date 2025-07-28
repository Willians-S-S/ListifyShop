package com.willians.ListifyShop.repository;

import com.willians.ListifyShop.entety.SharedList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShareListRepository extends JpaRepository<SharedList, String> {
    Optional<SharedList> findById(String id);
}
