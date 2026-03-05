package com.brunopressi.controleFinanceiro.entities.dto.usuarioDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
public class AuthDTO {

    @NotBlank(message = "Campo email não pode estar vazio.")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Email inválido")
    private String email;

    @NotBlank(message = "Campo senha não pode estar vazio.")
    @Size(min = 4, max = 12, message = "Tamanho da senha deve estar entre 4 e 12 caracteres")
    private String senha;
}
