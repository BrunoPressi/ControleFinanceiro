package com.brunopressi.controleFinanceiro.entities.dto.usuarioDTO;

import com.brunopressi.controleFinanceiro.entities.enums.Role;
import lombok.Data;

@Data
public class UsuarioResponseDTO {

    private Long id;
    private String nome;
    private String email;
    private Role role;

}
