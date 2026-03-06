package com.brunopressi.controleFinanceiro.entities.dto.usuarioDTO;

import jakarta.validation.constraints.Pattern;

public record UsuarioUpdateDTO (

    @Pattern(regexp = "^(?!\\s+$)[A-Za-zÀ-ÿ ]{4,40}$", message = "Nome deve estar entre 4 e 40 caracteres")
    String nome,

    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Email inválido")
    String email
) {}
