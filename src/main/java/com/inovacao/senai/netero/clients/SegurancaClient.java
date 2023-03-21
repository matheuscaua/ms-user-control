package com.inovacao.senai.netero.clients;

import com.inovacao.senai.netero.modelos.dtos.CredencialDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "seguranca", url = "http://localhost:8200/")
public interface SegurancaClient {

    @PostMapping("/cadastrarCredencial")
    ResponseEntity cadastrarCredencial(CredencialDTO credencialDTO);

}
