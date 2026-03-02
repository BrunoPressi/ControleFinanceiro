package com.brunopressi.controleFinanceiro.entities.dto.usuarioDTO;

import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UsuarioUpdateDTO {

    @Pattern(regexp = "^(?!\\s+$)[A-Za-zÀ-ÿ ]{4,40}$", message = "Nome deve estar entre 4 e 40 caracteres")
    private String nome;

    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Email inválido")
    private String email;

}
