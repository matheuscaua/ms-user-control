package com.inovacao.senai.netero.clients;

import com.inovacao.senai.netero.modelos.dtos.ViaCepDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "viacep", url = "https://viacep.com.br/")
public interface ViaCepClient {

    @GetMapping("ws/{cep}/json")
    ViaCepDTO buscarDadosViaCep(@PathVariable("cep") String cep);

}
