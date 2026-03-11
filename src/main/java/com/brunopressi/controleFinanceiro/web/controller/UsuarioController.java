package com.brunopressi.controleFinanceiro.web.controller;

import com.brunopressi.controleFinanceiro.entities.Usuario;
import com.brunopressi.controleFinanceiro.entities.dto.mappers.UsuarioMapper;
import com.brunopressi.controleFinanceiro.entities.dto.usuarioDTO.UsuarioCreateDTO;
import com.brunopressi.controleFinanceiro.entities.dto.usuarioDTO.UsuarioResponseDTO;
import com.brunopressi.controleFinanceiro.entities.dto.usuarioDTO.UsuarioUpdateDTO;
import com.brunopressi.controleFinanceiro.jwt.JwtUserDetails;
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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.ErrorManager;

@Tag(name = "Usuario Controller", description = "Controlador com recursos relacionados a entidade/objeto usuario")
@RestController
@RequestMapping("/api/v1/usuario")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final UsuarioMapper usuarioMapper;

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
        com.brunopressi.controleFinanceiro.entities.Usuario usuario = usuarioService.create(usuarioCreateDTO);

        return ResponseEntity.status(201).body(usuarioMapper.toDto(usuario));
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
    @GetMapping("/me")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<UsuarioResponseDTO> buscarUsuario(@AuthenticationPrincipal JwtUserDetails userDetails) {
        com.brunopressi.controleFinanceiro.entities.Usuario usuario = usuarioService.findById(userDetails.getId());

        return ResponseEntity.ok(usuarioMapper.toDto(usuario));
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
    @DeleteMapping("/me")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Void> deletarUsuario(@AuthenticationPrincipal JwtUserDetails userDetails) {
        usuarioService.delete(userDetails.getId());

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
    @PatchMapping("/me")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<UsuarioResponseDTO> atualizarUsuario(@AuthenticationPrincipal JwtUserDetails userDetails, @RequestBody @Valid UsuarioUpdateDTO usuarioUpdateDTO) {
        com.brunopressi.controleFinanceiro.entities.Usuario usuario = usuarioService.patch(userDetails.getId(), usuarioUpdateDTO);

        return ResponseEntity.ok(usuarioMapper.toDto(usuario));
    }
}
