package com.inovacao.senai.netero.controladores.entidades;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "tb_usuario")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Usuario implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String senha;

    private String sobrenome;

    private String email;

    private String cpf;

    private String rg;

    @OneToOne(mappedBy = "usuario", orphanRemoval = true, cascade = CascadeType.ALL)
    private Endereco endereco;

    @OneToMany(mappedBy = "usuario", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Telefone> telefones = new ArrayList<>();

}
