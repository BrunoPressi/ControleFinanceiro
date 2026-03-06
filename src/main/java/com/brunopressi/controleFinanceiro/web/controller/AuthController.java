package com.brunopressi.controleFinanceiro.web.controller;

import com.brunopressi.controleFinanceiro.entities.dto.usuarioDTO.AuthDTO;
import com.brunopressi.controleFinanceiro.jwt.JwtToken;
import com.brunopressi.controleFinanceiro.service.AuthService;
import com.brunopressi.controleFinanceiro.web.exception.ErrorMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Controlador de autenticação", description = "Recurso para autenticar um usuário por meio de um Token JWT")
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @Operation(responses = {
            @ApiResponse(responseCode = "200", description = "Usuário autenticado com sucesso",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = JwtToken.class))),
            @ApiResponse(responseCode = "400", description = "Senha inválida/Dados inválidos",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
    })
    @PostMapping
    public ResponseEntity<JwtToken> auth(@RequestBody @Valid AuthDTO authDTO) {
        JwtToken jwtToken = authService.auth(authDTO);

        return ResponseEntity.ok(jwtToken);
    }

}
