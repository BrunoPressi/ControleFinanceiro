package com.brunopressi.controleFinanceiro.web.controller;

import com.brunopressi.controleFinanceiro.service.DespesaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/despesa")
@RequiredArgsConstructor
public class DespesaController {

    private final DespesaService despesaService;

}
