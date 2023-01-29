package com.inovacao.senai.netero.repositorios;

import com.inovacao.senai.netero.enums.ParametroEnum;
import com.inovacao.senai.netero.modelos.entidades.Parametro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParametroRepositorio extends JpaRepository<Parametro, Long> {

    Parametro findParametroByNomeParametro(ParametroEnum nomeParametro);

}
