package com.inovacao.senai.netero.repositorios;

import com.inovacao.senai.netero.modelos.entidades.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpresaRepositorio extends JpaRepository<Empresa, Long> {
}
