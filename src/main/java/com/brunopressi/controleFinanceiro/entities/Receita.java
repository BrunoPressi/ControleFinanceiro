package com.brunopressi.controleFinanceiro.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "receitas")
@Data
public class Receita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal valor;

    @Column(nullable = false)
    private LocalDate data;

    @Column(columnDefinition = "VARCHAR(100) DEFAULT 'SEM DESCRIÇÃO'")
    private String descricao;

    @ManyToOne(fetch = FetchType.EAGER)
    private Usuario usuario;

}
