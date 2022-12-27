package com.inovacao.senai.netero.servicos.modelos.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table(name = "tb_telefone")
public class Telefone {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String numero;

    @JsonIgnore
    @org.hibernate.annotations.ForeignKey(name = "id_usuario")
    @ManyToOne
    private Usuario usuario;

}
