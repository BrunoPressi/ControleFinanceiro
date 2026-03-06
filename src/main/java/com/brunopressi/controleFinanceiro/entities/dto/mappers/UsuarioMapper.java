package com.brunopressi.controleFinanceiro.entities.dto.mappers;

import com.brunopressi.controleFinanceiro.entities.Usuario;
import com.brunopressi.controleFinanceiro.entities.dto.usuarioDTO.UsuarioCreateDTO;
import com.brunopressi.controleFinanceiro.entities.dto.usuarioDTO.UsuarioResponseDTO;
import com.brunopressi.controleFinanceiro.entities.dto.usuarioDTO.UsuarioUpdateDTO;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDto(UsuarioUpdateDTO usuarioUpdateDTO, @MappingTarget Usuario usuario);

    UsuarioResponseDTO toDto(Usuario usuario);
    Usuario toEntity(UsuarioCreateDTO usuarioCreateDTO);
}
