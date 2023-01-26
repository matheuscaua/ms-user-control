package com.inovacao.senai.netero.modelos.entidades;


import com.inovacao.senai.netero.enums.ParametroEnum;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Parametro {

    @Id
    private Long id;

    private ParametroEnum nomeParametro;

    private String valorParametro;

}
