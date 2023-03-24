package com.inovacao.senai.netero.modelos.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;


@Data
@Entity
@Table(name = "tb_telefone")
public class Telefone {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String numero;

    @JsonIgnore
    @ManyToOne
    private Usuario usuario;

    @JsonIgnore
    @ManyToOne
    private Empresa empresa;

}
