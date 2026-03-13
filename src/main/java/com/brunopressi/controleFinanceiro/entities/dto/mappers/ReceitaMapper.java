package com.brunopressi.controleFinanceiro.entities.dto.mappers;

import com.brunopressi.controleFinanceiro.entities.Receita;
import com.brunopressi.controleFinanceiro.entities.Usuario;
import com.brunopressi.controleFinanceiro.entities.dto.receitaDTO.ReceitaCreateDTO;
import com.brunopressi.controleFinanceiro.entities.dto.receitaDTO.ReceitaResponseDTO;
import com.brunopressi.controleFinanceiro.entities.dto.receitaDTO.ReceitaUpdateDTO;
import com.brunopressi.controleFinanceiro.entities.dto.usuarioDTO.UsuarioUpdateDTO;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReceitaMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDto(ReceitaUpdateDTO receitaUpdateDTO, @MappingTarget Receita receita);

    ReceitaResponseDTO toDto(Receita receita);
    Receita toEntity(ReceitaCreateDTO receitaCreateDTO);
    List<ReceitaResponseDTO> toDtoList(Page<Receita> receitaList);

}
