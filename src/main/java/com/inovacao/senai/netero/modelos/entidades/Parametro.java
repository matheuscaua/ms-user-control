package com.inovacao.senai.netero.modelos.entidades;


import com.inovacao.senai.netero.enums.ParametroEnum;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Parametro {

    @Id
    private Long id;

    private ParametroEnum nomeParametro;

    private String valorParametro;

}
