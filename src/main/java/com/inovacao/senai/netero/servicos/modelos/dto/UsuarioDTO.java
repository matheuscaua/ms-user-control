package com.inovacao.senai.netero.servicos.modelos.dto;

import com.inovacao.senai.netero.servicos.modelos.entidades.Endereco;
import com.inovacao.senai.netero.servicos.modelos.entidades.Telefone;
import lombok.Data;
import lombok.NonNull;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Data
public class UsuarioDTO {

    @NotEmpty(message = "Nome obrigatório...")
    private String nome;

    @NotEmpty(message = "Senha obrigatória...")
    private String senha;

    @NotEmpty(message = "Sobrenome obrigatório...")
    private String sobrenome;

    @Email(message = "Email inválido...")
    @NotEmpty(message = "Email obrigatório...")
    private String email;

    @CPF(message = "Cpf inválido...")
    @NotEmpty(message = "Cpf obrigatório...")
    private String cpf;

    @NotEmpty(message = "Rg obrigatório...")
    private String rg;

    @NonNull
    private List<Telefone> telefones = new ArrayList<>();

    private Endereco endereco;

}
