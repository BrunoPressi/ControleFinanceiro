package com.brunopressi.controleFinanceiro.entities.dto.mappers;

import com.brunopressi.controleFinanceiro.entities.Receita;
import com.brunopressi.controleFinanceiro.entities.dto.receitaDTO.ReceitaCreateDTO;
import com.brunopressi.controleFinanceiro.entities.dto.receitaDTO.ReceitaResponseDTO;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReceitaMapper {

    ReceitaResponseDTO toDto(Receita receita);
    Receita toEntity(ReceitaCreateDTO receitaCreateDTO);
    List<ReceitaResponseDTO> toDtoList(Page<Receita> receitaList);

}
