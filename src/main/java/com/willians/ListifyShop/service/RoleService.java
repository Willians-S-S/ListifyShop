package com.willians.ListifyShop.service;

import com.willians.ListifyShop.entety.Role;
import com.willians.ListifyShop.enums.RoleName;
import com.willians.ListifyShop.exception.NotFoundException;
import com.willians.ListifyShop.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    @Autowired
    RoleRepository roleRepository;

    public Role findByName(RoleName name){
        return roleRepository.findByName(name).orElseThrow(() -> new NotFoundException("Role não encontrada."));
    }
}
