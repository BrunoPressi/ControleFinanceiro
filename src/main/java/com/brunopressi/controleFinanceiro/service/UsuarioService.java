package com.brunopressi.controleFinanceiro.service;

import com.brunopressi.controleFinanceiro.entities.Usuario;
import com.brunopressi.controleFinanceiro.entities.dto.mappers.UsuarioMapper;
import com.brunopressi.controleFinanceiro.entities.dto.usuarioDTO.UsuarioCreateDTO;
import com.brunopressi.controleFinanceiro.entities.dto.usuarioDTO.UsuarioResponseDTO;
import com.brunopressi.controleFinanceiro.entities.dto.usuarioDTO.UsuarioUpdateDTO;
import com.brunopressi.controleFinanceiro.entities.dto.mappers.ObjectMapper;
import com.brunopressi.controleFinanceiro.exception.DuplicateEntityException;
import com.brunopressi.controleFinanceiro.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

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

    @Transactional(readOnly = true)
    public UsuarioResponseDTO findById(Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("Usuario %d não encontrado", id))
        );
        return ObjectMapper.parseObject(usuario, UsuarioResponseDTO.class);
    }

    @Transactional(readOnly = true)
    public List<UsuarioResponseDTO> findAll() {
        List<Usuario> usuarioList = usuarioRepository.findAll();
        return ObjectMapper.parseObjectList(usuarioList, UsuarioResponseDTO.class);
    }

    @Transactional(readOnly = false)
    public void delete(Long id) {
        this.findById(id);
        usuarioRepository.deleteById(id);
    }

    @Transactional(readOnly = false)
    public UsuarioResponseDTO patch(Long id, UsuarioUpdateDTO usuarioUpdateDTO) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("Usuario %d não encontrado", id))
        );

        try {
            usuarioMapper.updateFromDto(usuarioUpdateDTO, usuario);
            usuarioRepository.flush();
        }
        catch (DataIntegrityViolationException e) {
            throw new DuplicateEntityException(String.format("Usuario com email %s já cadastrado", usuarioUpdateDTO.getEmail()));
        }

        return ObjectMapper.parseObject(usuario, UsuarioResponseDTO.class);
    }
}
