package com.brunopressi.controleFinanceiro.web.controller;

import com.brunopressi.controleFinanceiro.entities.Receita;
import com.brunopressi.controleFinanceiro.entities.dto.PageableDTO;
import com.brunopressi.controleFinanceiro.entities.dto.mappers.PageableMapper;
import com.brunopressi.controleFinanceiro.entities.dto.mappers.ReceitaMapper;
import com.brunopressi.controleFinanceiro.entities.dto.receitaDTO.ReceitaCreateDTO;
import com.brunopressi.controleFinanceiro.entities.dto.receitaDTO.ReceitaResponseDTO;
import com.brunopressi.controleFinanceiro.entities.dto.receitaDTO.ReceitaUpdateDTO;
import com.brunopressi.controleFinanceiro.jwt.JwtUserDetails;
import com.brunopressi.controleFinanceiro.repository.ReceitaProjection;
import com.brunopressi.controleFinanceiro.service.ReceitaService;
import com.brunopressi.controleFinanceiro.web.exception.ErrorMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Receita Controller", description = "Controllador com recursos relacionados a entidade/objeto Receita")
@RestController
@RequestMapping("/api/v1/receita")
@RequiredArgsConstructor
public class ReceitaController {

    private final ReceitaService receitaService;
    private final ReceitaMapper receitaMapper;
    private final PageableMapper pageableMapper;

    @Operation(summary = "Criar uma nova receita", description = "Recurso para criar uma nova receita. " +
            "Requisição necessita de um TOKEN JWT válido",
            security = @SecurityRequirement(name = "security"),
            responses = {
                @ApiResponse(responseCode = "200", description = "Receita criada com sucesso",
                        content = @Content(mediaType = "application/json", schema = @Schema(implementation = ReceitaResponseDTO.class))),
                @ApiResponse(responseCode = "400", description = "Dados de entrada inválidos",
                        content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
                @ApiResponse(responseCode = "401", description = "Token JWT inválido ou ausente",
                        content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
            }
    )
    @PostMapping()
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ReceitaResponseDTO> novaReceita(@RequestBody @Valid ReceitaCreateDTO receitaCreateDTO, @AuthenticationPrincipal JwtUserDetails userDetails) {
        Receita receita = receitaService.create(receitaCreateDTO, userDetails);

        return ResponseEntity.status(HttpStatus.CREATED).body(receitaMapper.toDto(receita));
    }

    @Operation(summary = "Buscar receita", description = "Recurso para buscar receita pelo ID." +
            "Requisição necessita de um TOKEN JWT válido",
            security = @SecurityRequirement(name = "security"),
            responses = {
                @ApiResponse(responseCode = "200", description = "Receita encontrada com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(contentSchema = ReceitaResponseDTO.class))),
                @ApiResponse(responseCode = "404", description = "Receita não encontrada",
                    content = @Content(mediaType = "application/json", schema = @Schema(contentSchema = ErrorMessage.class))),
                @ApiResponse(responseCode = "401", description = "Token JWT inválido ou ausente",
                    content = @Content(mediaType = "application/json", schema = @Schema(contentSchema = ErrorMessage.class)))

            }
    )
    @GetMapping("/{receitaId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ReceitaResponseDTO> buscarReceita(@PathVariable Long receitaId, @AuthenticationPrincipal JwtUserDetails userDetails) {
        Receita receita = receitaService.findById(receitaId, userDetails.getId());

        return ResponseEntity.ok(receitaMapper.toDto(receita));
    }

    @Operation(summary = "Buscar todas as receitas", description = "Recurso para buscar todas as receitas do usuario." +
            "Requisição necessita e um TOKEN JWT válido",
            security = @SecurityRequirement(name = "security"),
            responses = {
                @ApiResponse(responseCode = "200", description = "Receitas buscadas com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(contentSchema = PageableDTO.class))),
                @ApiResponse(responseCode = "401", description = "Token JWT inválido ou ausente",
                    content = @Content(mediaType = "application/json", schema = @Schema(contentSchema = ErrorMessage.class)))
            }
    )
    @GetMapping()
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<PageableDTO> buscarTodasReceitas(@Parameter(hidden = true) @PageableDefault(size = 2, sort = "id", direction = Sort.Direction.ASC) Pageable pageable,  @AuthenticationPrincipal JwtUserDetails userDetails) {
        Page<ReceitaProjection> receitaList = receitaService.findAll(pageable, userDetails.getId());

        return ResponseEntity.ok(pageableMapper.toDto(receitaList));
    }

    @Operation(summary = "Atualizar receita", description = "Recurso para atualizar receitas do usuario." +
            "Requisição necessita e um TOKEN JWT válido",
            security = @SecurityRequirement(name = "security"),
            responses = {
                @ApiResponse(responseCode = "200", description = "Receita atualizada com sucesso",
                        content = @Content(mediaType = "application/json", schema = @Schema(contentSchema = PageableDTO.class))),
                @ApiResponse(responseCode = "401", description = "Token JWT inválido ou ausente",
                        content = @Content(mediaType = "application/json", schema = @Schema(contentSchema = ErrorMessage.class))),
                @ApiResponse(responseCode = "404", description = "Receita não encontrada",
                        content = @Content(mediaType = "application/json", schema = @Schema(contentSchema = ErrorMessage.class))),
                @ApiResponse(responseCode = "400", description = "Dados de entrada inválidos",
                        content = @Content(mediaType = "application/json", schema = @Schema(contentSchema = ErrorMessage.class)))
            }
    )
    @PatchMapping("/{receitaId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ReceitaResponseDTO> atualizarReceita(@PathVariable Long receitaId, @RequestBody @Valid ReceitaUpdateDTO receitaUpdateDTO, @AuthenticationPrincipal JwtUserDetails userDetails) {
        Receita receita = receitaService.patchReceita(receitaId, receitaUpdateDTO, userDetails.getId());

        return ResponseEntity.ok(receitaMapper.toDto(receita));
    }

    @Operation(summary = "Deletar receita", description = "Recurso para deletar receitas do usuario." +
            "Requisição necessita e um TOKEN JWT válido",
            security = @SecurityRequirement(name = "security"),
            responses = {
                    @ApiResponse(responseCode = "204", description = "Receita deletada com sucesso",
                            content = @Content(mediaType = "application/json", schema = @Schema(contentSchema = PageableDTO.class))),
                    @ApiResponse(responseCode = "401", description = "Token JWT inválido ou ausente",
                            content = @Content(mediaType = "application/json", schema = @Schema(contentSchema = ErrorMessage.class))),
                    @ApiResponse(responseCode = "404", description = "Receita não encontrada",
                            content = @Content(mediaType = "application/json", schema = @Schema(contentSchema = ErrorMessage.class)))
            }
    )
    @DeleteMapping("{receitaId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Void> deletarReceita(@PathVariable Long receitaId, @AuthenticationPrincipal JwtUserDetails userDetails) {
        receitaService.deleteById(receitaId, userDetails.getId());

        return ResponseEntity.noContent().build();
    }
}
