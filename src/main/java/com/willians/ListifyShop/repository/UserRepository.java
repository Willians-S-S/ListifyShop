package com.willians.ListifyShop.repository;

import com.willians.ListifyShop.entety.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, String> {
    public Optional<User> findByEmail(String email);
    public Optional<User> findByCpf(String cpf);
}
