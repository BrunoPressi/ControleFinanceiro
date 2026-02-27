package com.brunopressi.controleFinanceiro.entities;

import com.brunopressi.controleFinanceiro.entities.enums.Categoria;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "despesas")
@Data
public class Despesa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal valor;

    @Column(nullable = false)
    private LocalDate data;

    @Column()
    private String descricao = "SEM DESCRIÇÃO";

    @Column(nullable = false)
    private Categoria categoria;

    @ManyToOne(fetch = FetchType.EAGER)
    private Usuario usuario;
}
