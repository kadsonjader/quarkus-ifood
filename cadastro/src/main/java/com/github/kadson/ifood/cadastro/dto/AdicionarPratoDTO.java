package com.github.kadson.ifood.cadastro.dto;

import com.github.kadson.ifood.cadastro.infra.DTO;
import com.github.kadson.ifood.cadastro.infra.ValidDTO;

import javax.validation.ConstraintValidatorContext;
import java.math.BigDecimal;

@ValidDTO
public class AdicionarPratoDTO implements DTO {

    public String nome;

    public String descricao;

    public BigDecimal preco;


}