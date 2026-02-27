package com.brunopressi.controleFinanceiro.service;

import com.brunopressi.controleFinanceiro.entities.Usuario;
import com.brunopressi.controleFinanceiro.entities.dto.UsuarioDTO.UsuarioCreateDTO;
import com.brunopressi.controleFinanceiro.entities.dto.UsuarioDTO.UsuarioResponseDTO;
import com.brunopressi.controleFinanceiro.entities.dto.mapper.ObjectMapper;
import com.brunopressi.controleFinanceiro.exception.DuplicateEntityException;
import com.brunopressi.controleFinanceiro.repository.UsuarioRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Transactional(readOnly = false)
    public UsuarioResponseDTO create(UsuarioCreateDTO usuarioCreateDTO) {
        Usuario usuario = ObjectMapper.parseObject(usuarioCreateDTO, Usuario.class);
        try {
            usuarioRepository.save(usuario);
        }
        catch(DataIntegrityViolationException e) {
            throw new DuplicateEntityException(String.format("Usuario com email %s já cadastrado.", usuario.getEmail()));
        }
        return ObjectMapper.parseObject(usuario, UsuarioResponseDTO.class);
    }
}
