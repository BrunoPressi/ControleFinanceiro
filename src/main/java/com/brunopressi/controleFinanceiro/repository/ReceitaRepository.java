package com.brunopressi.controleFinanceiro.repository;

import com.brunopressi.controleFinanceiro.entities.Receita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceitaRepository extends JpaRepository<Receita, Long> {
}
