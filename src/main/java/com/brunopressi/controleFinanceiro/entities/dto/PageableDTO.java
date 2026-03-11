package com.brunopressi.controleFinanceiro.entities.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record PageableDTO(
        List content,
        boolean first,
        boolean last,

        @JsonProperty("page")
        int number,
        int size,

        @JsonProperty("pageElements")
        int numberOfElements,
        int totalPages,
        int totalElements
) {}
