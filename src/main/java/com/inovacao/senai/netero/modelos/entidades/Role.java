package com.inovacao.senai.netero.modelos.entidades;

import com.inovacao.senai.netero.enums.RoleEnum;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "tb_role")
public class Role implements GrantedAuthority, Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private RoleEnum role;

    @Override
    public String getAuthority() {
        return this.role.toString();
    }
}
