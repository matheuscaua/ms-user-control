package com.inovacao.senai.netero.modelos.entidades;


import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Cascade;


import java.io.Serializable;
import java.util.UUID;

@Data
@Entity
@Table(name = "tb_endereco")
public class Endereco{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String cep;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String localidade;
    private String uf;

    @JsonIgnore
    @OneToOne
    @Cascade(org.hibernate.annotations.CascadeType.DETACH)
    private Usuario usuario;
    @JsonIgnore
    @OneToOne
    private Empresa empresa;
}
