package com.inovacao.senai.netero.modelos.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Entity(name = "tb_usuario")
@Data
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String nome;

    private String senha;

    private String sobrenome;

    private String telefone;

    private String email;

    private String cpf;

    private String rg;

    private boolean ativo;
}
