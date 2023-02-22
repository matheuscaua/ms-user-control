package com.inovacao.senai.netero.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inovacao.senai.netero.enums.RoleEnum;
import com.inovacao.senai.netero.modelos.entidades.Role;

public interface RoleRepositorio extends JpaRepository<Role, Long> {

    Role findByIdentificador(RoleEnum identificador);

}
