package com.brunopressi.controleFinanceiro.service;

import com.brunopressi.controleFinanceiro.entities.Receita;
import com.brunopressi.controleFinanceiro.entities.dto.mappers.ReceitaMapper;
import com.brunopressi.controleFinanceiro.entities.dto.receitaDTO.ReceitaCreateDTO;
import com.brunopressi.controleFinanceiro.jwt.JwtUserDetails;
import com.brunopressi.controleFinanceiro.repository.ReceitaProjection;
import com.brunopressi.controleFinanceiro.repository.ReceitaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class ReceitaService {

    private final ReceitaRepository receitaRepository;
    private final ReceitaMapper receitaMapper;

    @Transactional(readOnly = false)
    public Receita create(ReceitaCreateDTO receitaCreateDTO, JwtUserDetails userDetails) {
        Receita receita = receitaMapper.toEntity(receitaCreateDTO);

        receita.setUsuario(userDetails.getUsuario());
        receitaRepository.save(receita);

        return receita;
    }

    @Transactional(readOnly = true)
    public Receita findById(Long receitaID, Long usuarioId) {
        Receita receita = receitaRepository.findByIdAndUsuarioId(receitaID, usuarioId).orElseThrow(
                () -> new EntityNotFoundException(String.format("Receita %s não encontrada", receitaID))
        );
        return receita;
    }

    @Transactional(readOnly = true)
    public Page<ReceitaProjection> findAll(Pageable pageable, Long id) {
        Page<ReceitaProjection> receitaList = receitaRepository.findAllByUsuarioId(pageable, id);
        return receitaList;
    }
}
