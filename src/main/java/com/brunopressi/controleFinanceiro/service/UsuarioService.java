package com.brunopressi.controleFinanceiro.service;

import com.brunopressi.controleFinanceiro.entities.Receita;
import com.brunopressi.controleFinanceiro.entities.Usuario;
import com.brunopressi.controleFinanceiro.entities.dto.mappers.UsuarioMapper;
import com.brunopressi.controleFinanceiro.entities.dto.usuarioDTO.UsuarioCreateDTO;
import com.brunopressi.controleFinanceiro.entities.dto.usuarioDTO.UsuarioResponseDTO;
import com.brunopressi.controleFinanceiro.entities.dto.usuarioDTO.UsuarioUpdateDTO;
import com.brunopressi.controleFinanceiro.exception.DuplicateEntityException;
import com.brunopressi.controleFinanceiro.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;
    private final PasswordEncoder passwordEncoder;

    @Transactional(readOnly = false)
    public Usuario create(UsuarioCreateDTO usuarioCreateDTO) {
        Usuario usuario = usuarioMapper.toEntity(usuarioCreateDTO);
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        try {
            usuarioRepository.save(usuario);
        }
        catch(DataIntegrityViolationException e) {
            throw new DuplicateEntityException(String.format("Usuario com email %s já cadastrado.", usuario.getEmail()));
        }
        return usuario;
    }

    @Transactional(readOnly = true)
    public Usuario findById(Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("Usuario %d não encontrado", id))
        );
        return usuario;
    }

    @Transactional(readOnly = false)
    public void delete(Long id) {
        this.findById(id);
        usuarioRepository.deleteById(id);
    }

    @Transactional(readOnly = false)
    public Usuario patch(Long id, UsuarioUpdateDTO usuarioUpdateDTO) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("Usuario %d não encontrado", id))
        );

        try {
            usuarioMapper.updateFromDto(usuarioUpdateDTO, usuario);
            usuarioRepository.flush();
        }
        catch (DataIntegrityViolationException e) {
            throw new DuplicateEntityException(String.format("Usuario com email %s já cadastrado", usuarioUpdateDTO.email()));
        }

        return usuario;
    }

    @Transactional(readOnly = true)
    public Usuario buscarPorEmail(String email) {
        Usuario usuario = usuarioRepository.findByEmail(email).orElseThrow(
                () -> new EntityNotFoundException(String.format("Usuario com email %s não encontrado",  email))
        );
        return usuario;
    }
}
