package com.brunopressi.controleFinanceiro.web.controller;

import com.brunopressi.controleFinanceiro.entities.dto.usuarioDTO.UsuarioCreateDTO;
import com.brunopressi.controleFinanceiro.entities.dto.usuarioDTO.UsuarioResponseDTO;
import com.brunopressi.controleFinanceiro.entities.dto.usuarioDTO.UsuarioUpdateDTO;
import com.brunopressi.controleFinanceiro.service.UsuarioService;
import com.brunopressi.controleFinanceiro.web.exception.ErrorMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.ErrorManager;

@Tag(name = "Usuario Controller", description = "Operações CRUD do usuario")
@RestController
@RequestMapping("/api/v1/usuario")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Operation(summary = "Criar um novo usuario", description = "Recurso para criar um novo usuario.",
        responses = {
            @ApiResponse(responseCode = "201", description = "Usuario criado com sucesso",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioResponseDTO.class))),
            @ApiResponse(responseCode = "409", description = "Usuario já cadastrado",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
            @ApiResponse(responseCode = "400", description = "Dados do usuario inválidos",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
        }
    )
    @PostMapping("/cadastro")
    public ResponseEntity<UsuarioResponseDTO> criarUsuario(@RequestBody @Valid UsuarioCreateDTO usuarioCreateDTO) {
        UsuarioResponseDTO usuarioResponseDTO = usuarioService.create(usuarioCreateDTO);

        return ResponseEntity.status(201).body(usuarioResponseDTO);
    }

    @Operation(summary = "Buscar um usuario", description = "Recurso para buscar um usuario. " +
            "Requisição necessita de um TOKEN JWT válido.",
            security = @SecurityRequirement(name= "security"),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Usuario encontrado com sucesso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioResponseDTO.class))),
                    @ApiResponse(responseCode = "404", description = "Usuario não encontrado",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
                    @ApiResponse(responseCode = "403", description = "Acesso não autorizado",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
                    @ApiResponse(responseCode = "401", description = "Usuário não autenticado",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> buscarUsuario(@PathVariable Long id) {
        UsuarioResponseDTO usuarioResponseDTO = usuarioService.findById(id);

        return ResponseEntity.ok(usuarioResponseDTO);
    }

    @Operation(summary = "Deletar um usuario", description = "Recurso para deletar um usuario. " +
            "Requisição necessita de um TOKEN JWT válido.",
            security = @SecurityRequirement(name= "security"),
            responses = {
                    @ApiResponse(responseCode = "204", description = "Usuario deletado com sucesso",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(responseCode = "404", description = "Usuario não encontrado",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
                    @ApiResponse(responseCode = "403", description = "Acesso não autorizado",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
                    @ApiResponse(responseCode = "401", description = "Usuário não autenticado",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable Long id) {
        usuarioService.delete(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Operation(summary = "Atualizar parcialmente um usuario", description = "Recurso para atualizar um usuario. " +
            "Requisição necessita de um TOKEN JWT válido.",
            security = @SecurityRequirement(name= "security"),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Usuario atualizado com sucesso",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(responseCode = "404", description = "Usuario não encontrado",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
                    @ApiResponse(responseCode = "403", description = "Acesso não autorizado",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
                    @ApiResponse(responseCode = "400", description = "Dados do usuario inválidos",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
                    @ApiResponse(responseCode = "401", description = "Usuário não autenticado",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
            }
    )
    @PatchMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> atualizarUsuario(@PathVariable Long id, @RequestBody @Valid UsuarioUpdateDTO usuarioUpdateDTO) {
        UsuarioResponseDTO usuarioResponseDTO = usuarioService.patch(id, usuarioUpdateDTO);

        return ResponseEntity.ok(usuarioResponseDTO);
    }
}
