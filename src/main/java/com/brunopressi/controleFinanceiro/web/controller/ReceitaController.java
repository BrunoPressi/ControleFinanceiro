package com.brunopressi.controleFinanceiro.web.controller;

import com.brunopressi.controleFinanceiro.service.ReceitaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/receita")
@RequiredArgsConstructor
public class ReceitaController {

    private final ReceitaService receitaService;

}
