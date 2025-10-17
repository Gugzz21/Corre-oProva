package com.senac.gustavo.dto;

import com.senac.gustavo.entity.RoleName;

public record CreateUserDto(

        // Change from 'email' to 'matricula'
        String matricula,

        // Change from 'password' to 'chaveAcesso'
        String chaveAcesso,

        RoleName role

) {
}