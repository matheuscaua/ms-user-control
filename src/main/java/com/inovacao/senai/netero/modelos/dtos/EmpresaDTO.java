package com.inovacao.senai.netero.modelos.dtos;

import com.inovacao.senai.netero.enums.SituacaoEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmpresaDTO {
    private String nome;
    private String cnpj;
    @Enumerated(EnumType.STRING)
    private SituacaoEnum situacao;
}
