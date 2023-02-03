package com.inovacao.senai.netero.modelos.entidades;


import com.inovacao.senai.netero.enums.RoleEnum;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;




@Data
@Entity
@Table(name = "tb_role")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RoleEnum identificador;

    @Override
    public String getAuthority() {
        return this.identificador.toString();
    }
}
