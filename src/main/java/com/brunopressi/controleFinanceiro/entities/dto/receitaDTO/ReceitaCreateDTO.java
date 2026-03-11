package com.brunopressi.controleFinanceiro.entities.dto.receitaDTO;

import com.brunopressi.controleFinanceiro.entities.Usuario;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ReceitaCreateDTO(
        @NotNull(message = "O valor é obrigatório")
        @Positive(message = "O valor precisa ser positivo")
        BigDecimal valor,

        @NotNull(message = "A data é obrigatória")
        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate data,

        String descricao
) {}
