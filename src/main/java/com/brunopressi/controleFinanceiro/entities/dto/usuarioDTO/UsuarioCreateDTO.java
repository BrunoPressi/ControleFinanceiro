package com.brunopressi.controleFinanceiro.entities.dto.usuarioDTO;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class UsuarioCreateDTO {

    @NotBlank(message = "Campo nome não pode estar vazio.")
    @Size(min = 4, max = 40, message = "Tamanho do nome deve estar entre 4 e 40 caracteres.")
    private String nome;

    @NotBlank(message = "Campo email não pode estar vazio.")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Email inválido")
    private String email;

    @NotBlank(message = "Campo senha não pode estar vazio.")
    @Size(min = 4, max = 12, message = "Tamanho da senha deve estar entre 4 e 12 caracteres")
    private String senha;

}
