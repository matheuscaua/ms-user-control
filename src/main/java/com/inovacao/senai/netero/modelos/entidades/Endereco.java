package com.inovacao.senai.netero.modelos.entidades;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Cascade;

import java.io.Serializable;

@Data
@Entity
@Table(name = "tb_endereco")
public class Endereco implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cep;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String localidade;
    private String uf;
    @OneToOne
    @Cascade(org.hibernate.annotations.CascadeType.DETACH)
    private Usuario usuario;
    @OneToOne
    private Empresa empresa;
}
