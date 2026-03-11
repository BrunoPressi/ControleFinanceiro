package com.brunopressi.controleFinanceiro.entities.dto.receitaDTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ReceitaResponseDTO(
        Long id,
        BigDecimal valor,

        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate data,
        String descricao
) {}
