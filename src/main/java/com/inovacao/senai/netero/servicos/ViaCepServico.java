package com.inovacao.senai.netero.servicos;


import com.inovacao.senai.netero.client.ViaCepClient;
import com.inovacao.senai.netero.modelos.dto.ViaCepDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ViaCepServico {

    @Autowired
    private ViaCepClient viaCepClient;


    public ViaCepDTO buscarDadosViaCep(String cep){
        return viaCepClient.buscarDadosViaCep(cep);
    }
}
