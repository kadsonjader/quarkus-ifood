package com.github.kadson.ifood.cadastro.dto;

import com.github.kadson.ifood.cadastro.Localizacao;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

public class AdicionarRestauranteDTO {

    public String propietario;

    public String cnpj;

    public String nomeFantasia;

    public LocalizacaoDTO localizacao;

}
