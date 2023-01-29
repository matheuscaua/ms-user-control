package com.inovacao.senai.netero.client;

import com.inovacao.senai.netero.modelos.dto.ViaCepDTO;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.net.URI;

@FeignClient(name = "viacep", url = "https://viacep.com.br/")
public interface ViaCepClient {

    @GetMapping("ws/{cep}/json")
    ViaCepDTO buscarDadosViaCep(URI baseUri, @PathVariable("cep") String cep);

}
