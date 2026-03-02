package com.brunopressi.controleFinanceiro.web.controller;

import com.brunopressi.controleFinanceiro.entities.dto.usuarioDTO.UsuarioCreateDTO;
import com.brunopressi.controleFinanceiro.entities.dto.usuarioDTO.UsuarioResponseDTO;
import com.brunopressi.controleFinanceiro.entities.dto.usuarioDTO.UsuarioUpdateDTO;
import com.brunopressi.controleFinanceiro.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> buscarUsuario(@PathVariable Long id) {
        UsuarioResponseDTO usuarioResponseDTO = usuarioService.findById(id);

        return ResponseEntity.ok(usuarioResponseDTO);
    }

    @GetMapping()
    public ResponseEntity<List<UsuarioResponseDTO>> buscarTodosUsuarios() {
        List<UsuarioResponseDTO> usuarioResponseDTOList = usuarioService.findAll();

        return ResponseEntity.ok(usuarioResponseDTOList);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable Long id) {
        usuarioService.delete(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> atualizarUsuario(@PathVariable Long id, @RequestBody @Valid UsuarioUpdateDTO usuarioUpdateDTO) {
        UsuarioResponseDTO usuarioResponseDTO = usuarioService.patch(id, usuarioUpdateDTO);

        return ResponseEntity.ok(usuarioResponseDTO);
    }
}
