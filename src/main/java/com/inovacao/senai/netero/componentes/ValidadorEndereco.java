package com.inovacao.senai.netero.componentes;

import com.inovacao.senai.netero.modelos.entidades.Endereco;
import com.inovacao.senai.netero.servicos.ViaCepServico;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@AllArgsConstructor
@NoArgsConstructor
public class ValidadorEndereco {

    @Autowired
    private ViaCepServico viaCepServico;

    public boolean verificarEndereco(Endereco endereco) {
        boolean flag = false;
        if (endereco.getLogradouro() == null || endereco.getLogradouro().isEmpty()) flag = true;
        if (endereco.getBairro() == null || endereco.getBairro().isEmpty()) flag = true;
        if (endereco.getLocalidade() == null || endereco.getLocalidade().isEmpty()) flag = true;
        if (endereco.getUf() == null || endereco.getUf().isEmpty()) flag = true;
        return flag;
    }

    public void adequarEndereco(Endereco endereco) {
        var viaCepDTO = viaCepServico.buscarDadosViaCep(endereco.getCep());
        if (viaCepDTO != null && (viaCepDTO.getLogradouro() != null && !viaCepDTO.getLogradouro().isEmpty())) {
            endereco.setLocalidade(viaCepDTO.getLocalidade());
            endereco.setBairro(viaCepDTO.getBairro());
            endereco.setUf(viaCepDTO.getUf());
            endereco.setLogradouro(viaCepDTO.getLogradouro());
            endereco.setCep(viaCepDTO.getCep());
        }
    }

}
