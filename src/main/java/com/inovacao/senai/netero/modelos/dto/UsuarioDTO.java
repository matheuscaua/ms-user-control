package com.inovacao.senai.netero.modelos.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

@Data
public class UsuarioDTO {

    @NotEmpty(message = "Nome obrigatório...")
    private String nome;

    @NotEmpty(message = "Sobrenome obrigatório")
    private String sobrenome;

    @NotEmpty(message = "telefone obrigatório")
    private String telefone;

    @Email(message = "Email inválido...")
    @NotEmpty(message = "Email obrigatório...")
    private String email;

    @CPF(message = "Cpf inválido...")
    @NotEmpty(message = "Cpf obrigatório...")
    private String cpf;

    @NotEmpty(message = "Rg obrigatório...")
    private String rg;

}
