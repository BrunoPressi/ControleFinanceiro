package com.brunopressi.controleFinanceiro.web.controller;

import com.brunopressi.controleFinanceiro.entities.dto.usuarioDTO.AuthDTO;
import com.brunopressi.controleFinanceiro.jwt.JwtToken;
import com.brunopressi.controleFinanceiro.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping
    public ResponseEntity<JwtToken> auth(@RequestBody @Valid AuthDTO authDTO) {
        JwtToken jwtToken = authService.auth(authDTO);

        return ResponseEntity.ok(jwtToken);
    }

}
