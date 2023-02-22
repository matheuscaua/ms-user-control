package com.inovacao.senai.netero.modelos.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "tb_telefone")
public class Telefone {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String numero;

    @JsonIgnore
    @ManyToOne
    private Usuario usuario;

    @JsonIgnore
    @ManyToOne
    private Empresa empresa;

}
