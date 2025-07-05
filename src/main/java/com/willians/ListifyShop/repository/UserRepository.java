package com.willians.ListifyShop.repository;

import com.willians.ListifyShop.entety.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}
