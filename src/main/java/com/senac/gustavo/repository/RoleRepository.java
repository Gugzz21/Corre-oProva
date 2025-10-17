package com.senac.gustavo.repository;

import com.senac.gustavo.entity.Role;
import com.senac.gustavo.entity.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByNome(RoleName nome);


}
