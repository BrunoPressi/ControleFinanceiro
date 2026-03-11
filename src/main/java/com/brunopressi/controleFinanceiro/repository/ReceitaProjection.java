package com.brunopressi.controleFinanceiro.repository;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface ReceitaProjection {

    Long getId();
    BigDecimal getValor();
    LocalDate getData();
    String getDescricao();

}
