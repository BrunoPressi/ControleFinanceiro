package com.brunopressi.controleFinanceiro.entities.dto.mapper;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ObjectMapper {

    private static final Mapper mapper = DozerBeanMapperBuilder.buildDefault();

    public static <O, D> D parseObject(O origin, Class<D> destination) {
        return mapper.map(origin, destination);
    }

    public static <O, D> List<D> parseObjectList(List<O> origin, Class<D> destination) {
        return origin.stream().map((x) -> mapper.map(x, destination)).toList();
    }

}
