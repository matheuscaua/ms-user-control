package com.inovacao.senai.netero.modelos.dtos;

import com.inovacao.senai.netero.modelos.entidades.Endereco;
import com.inovacao.senai.netero.modelos.entidades.Role;
import com.inovacao.senai.netero.modelos.entidades.Telefone;

import lombok.Data;

import java.util.List;

@Data
public class UsuarioDTO {

    private String nome;

    private String senha;

    private String sobrenome;

    private String email;

    private String cpf;

    private String rg;

    private List<Role> roles;

}
