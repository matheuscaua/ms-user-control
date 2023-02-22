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

    public boolean verificarAdequacaoEndereco(Endereco endereco) {
        boolean flag = endereco.getLogradouro() == null || endereco.getLogradouro().isEmpty();
        if (endereco.getBairro() == null || endereco.getBairro().isEmpty()) flag = true;
        if (endereco.getLocalidade() == null || endereco.getLocalidade().isEmpty()) flag = true;
        if (endereco.getUf() == null || endereco.getUf().isEmpty()) flag = true;
        return flag;
    }

    public void adequarEndereco(Endereco endereco) {
        var viaCepDTO = viaCepServico.buscarDadosViaCep(endereco.getCep());
        if (viaCepDTO != null && (viaCepDTO.getLogradouro() != null && !viaCepDTO.getLogradouro().isEmpty())) {
            if (!endereco.getBairro().equals(viaCepDTO.getBairro()) || endereco.getBairro().isEmpty()) {
                endereco.setBairro(viaCepDTO.getBairro());
            }
            if (!endereco.getLogradouro().equals(viaCepDTO.getLogradouro())) {
                endereco.setLogradouro(viaCepDTO.getLogradouro());
            }
            if (!endereco.getLocalidade().equals(viaCepDTO.getLocalidade())) {
                endereco.setLocalidade(viaCepDTO.getLocalidade());
            }
            if (!endereco.getUf().equals(viaCepDTO.getUf())) {
                endereco.setUf(viaCepDTO.getUf());
            }
        }
    }

}
