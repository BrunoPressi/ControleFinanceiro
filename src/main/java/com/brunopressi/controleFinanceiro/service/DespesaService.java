package com.brunopressi.controleFinanceiro.service;

import com.brunopressi.controleFinanceiro.repository.DespesaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DespesaService {

    private final DespesaRepository despesaRepository;

}
