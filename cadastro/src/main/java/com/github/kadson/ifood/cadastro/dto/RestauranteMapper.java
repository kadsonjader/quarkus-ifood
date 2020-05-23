package com.github.kadson.ifood.cadastro.dto;

import com.github.kadson.ifood.cadastro.Restaurante;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "cdi")
public interface RestauranteMapper {

    @Mapping(target = "nome", source = "nomeFantasia")
    public Restaurante toRestaurante(AdicionarRestauranteDTO dto);
}
