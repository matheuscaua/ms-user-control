package com.inovacao.senai.netero.modelos.dtos;

import com.inovacao.senai.netero.modelos.entidades.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AutenticacaoDTO {

    private String email;
    private String senha;

    private List<Role> roles;
}
