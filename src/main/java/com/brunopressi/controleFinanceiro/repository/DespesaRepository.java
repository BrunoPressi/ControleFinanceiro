package com.brunopressi.controleFinanceiro.repository;

import com.brunopressi.controleFinanceiro.entities.Despesa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DespesaRepository extends JpaRepository<Despesa, Long> {
}
