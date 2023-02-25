package com.inovacao.senai.netero.modelos.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AutenticacaoDTO {

    private String email;
    private String senha;

}
