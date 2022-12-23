package com.inovacao.senai.netero.modelos.entidades;

import javax.persistence.*;

@Entity
@Table(name = "tb_telefone")
public class Telefone {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String numero;

    @org.hibernate.annotations.ForeignKey(name = "usuario_id")
    @ManyToOne
    private Usuario usuario;

}
