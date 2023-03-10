package com.inovacao.senai.netero.modelos.dtos;

import com.inovacao.senai.netero.enums.SituacaoEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class EmpresaDTO {

    private Long id;

    private String nome;

    @Enumerated(EnumType.STRING)
    private SituacaoEnum situacao;
}
