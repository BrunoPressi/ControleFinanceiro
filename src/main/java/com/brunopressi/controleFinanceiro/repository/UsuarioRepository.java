package com.brunopressi.controleFinanceiro.repository;

import com.brunopressi.controleFinanceiro.entities.Receita;
import com.brunopressi.controleFinanceiro.entities.Usuario;
import com.brunopressi.controleFinanceiro.entities.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);

    Optional<Usuario> findRoleByEmail(String email);

    Optional<Receita> findReceitaById(Long receitaID);
}
