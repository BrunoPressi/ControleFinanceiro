package com.brunopressi.controleFinanceiro.entities.dto.usuarioDTO;

import com.brunopressi.controleFinanceiro.entities.enums.Role;

public record UsuarioResponseDTO (
        Long id,
        String nome,
        String email,
        Role role
) {}
