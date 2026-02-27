package com.brunopressi.controleFinanceiro.web.controller;

import com.brunopressi.controleFinanceiro.entities.dto.UsuarioDTO.UsuarioCreateDTO;
import com.brunopressi.controleFinanceiro.entities.dto.UsuarioDTO.UsuarioResponseDTO;
import com.brunopressi.controleFinanceiro.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/usuario")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping()
    public ResponseEntity<UsuarioResponseDTO> criarUsuario(@RequestBody @Valid UsuarioCreateDTO usuarioCreateDTO) {
        UsuarioResponseDTO usuarioResponseDTO = usuarioService.create(usuarioCreateDTO);

        return ResponseEntity.status(201).body(usuarioResponseDTO);
    }

}
