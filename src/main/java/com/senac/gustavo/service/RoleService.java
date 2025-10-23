package com.senac.gustavo.service;

import com.senac.gustavo.entity.Role;
import com.senac.gustavo.entity.RoleName;
import com.senac.gustavo.repository.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role getRolesByName(RoleName roleName){
        return roleRepository.findByNome(roleName).orElse(null);
    }

}