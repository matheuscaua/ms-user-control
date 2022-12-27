package com.inovacao.senai.netero.servicos.modelos.entidades;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
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

    @JsonIgnore
    @org.hibernate.annotations.ForeignKey(name = "usuario_id")
    @OneToOne
    private Usuario usuario;
}
