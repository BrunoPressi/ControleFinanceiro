package com.brunopressi.controleFinanceiro.entities.dto.mappers;

import com.brunopressi.controleFinanceiro.entities.dto.PageableDTO;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public interface PageableMapper {

    PageableDTO toDto(Page page);

}
