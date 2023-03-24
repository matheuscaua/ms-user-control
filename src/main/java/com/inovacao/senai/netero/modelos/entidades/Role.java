package com.inovacao.senai.netero.modelos.entidades;


import com.inovacao.senai.netero.enums.RoleEnum;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.UUID;

@Data
@Entity
@Table(name = "tb_role")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RoleEnum identificador;

    @Override
    public String getAuthority() {
        return this.identificador.toString();
    }
}
