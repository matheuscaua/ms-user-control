package com.inovacao.senai.netero.clients;

import com.inovacao.senai.netero.modelos.dtos.CredencialDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "ms-authorization-server")
public interface SegurancaClient {

    @PostMapping("/cadastrarCredencial")
    ResponseEntity cadastrarCredencial(CredencialDTO credencialDTO);

}
