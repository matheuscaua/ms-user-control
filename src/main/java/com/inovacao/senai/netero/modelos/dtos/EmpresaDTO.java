package com.inovacao.senai.netero.modelos.dtos;

import com.inovacao.senai.netero.modelos.entidades.Endereco;
import com.inovacao.senai.netero.modelos.entidades.Telefone;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NonNull;
import org.hibernate.validator.constraints.br.CNPJ;

import java.util.ArrayList;
import java.util.List;

@Data
public class EmpresaDTO {

    @NotEmpty(message = "Nome obrigatório...")
    private String nome;
    @NotEmpty(message = "CNPJ obrigatório...")
    @CNPJ
    private String cnpj;
    @NotEmpty(message = "Senha obrigatória...")
    private String senha;
    private boolean situacao;
    @NotEmpty(message = "Email obrigatória...")
    @Email
    private String email;
    private String dt_cadastro;


    private Endereco endereco;

    @NonNull
    private List<Telefone> telefones = new ArrayList<>();
}
