package com.brunopressi.controleFinanceiro.service;

import com.brunopressi.controleFinanceiro.repository.ReceitaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ReceitaService {

    private final ReceitaRepository receitaRepository;

}
