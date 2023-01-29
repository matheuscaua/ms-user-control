package com.inovacao.senai.netero.modelos.entidades;


import com.inovacao.senai.netero.enums.ParametroEnum;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Parametro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ParametroEnum nomeParametro;

    private String valorParametro;

}
