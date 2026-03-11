package com.brunopressi.controleFinanceiro.repository;

import com.brunopressi.controleFinanceiro.entities.Receita;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReceitaRepository extends JpaRepository<Receita, Long> {

    Optional<Receita> findByIdAndUsuarioId(Long receitaID, Long usuarioId);

    Page<ReceitaProjection> findAllByUsuarioId(Pageable pageable, Long id);
}
