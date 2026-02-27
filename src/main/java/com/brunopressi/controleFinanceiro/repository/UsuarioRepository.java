package com.brunopressi.controleFinanceiro.repository;

import com.brunopressi.controleFinanceiro.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
