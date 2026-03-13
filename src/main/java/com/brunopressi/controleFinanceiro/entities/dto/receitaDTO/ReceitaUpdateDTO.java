package com.brunopressi.controleFinanceiro.entities.dto.receitaDTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ReceitaUpdateDTO(
        @Positive(message = "O valor precisa ser positivo")
        BigDecimal valor,

        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate data,

        String descricao
) {}
