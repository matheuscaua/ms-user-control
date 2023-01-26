package com.inovacao.senai.netero.repositorios;

import com.inovacao.senai.netero.enums.ParametroEnum;
import com.inovacao.senai.netero.modelos.entidades.Parametro;
import org.springframework.stereotype.Repository;

@Repository
public interface ParametroRepositorio {

    Parametro findParametroByNomeParametro(ParametroEnum nomeParametro);


}
