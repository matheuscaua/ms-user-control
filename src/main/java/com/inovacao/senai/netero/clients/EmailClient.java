package com.inovacao.senai.netero.clients;

import com.inovacao.senai.netero.modelos.dtos.EmailDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "email", url = "http://MS-EMAIL")
public interface EmailClient {
    @PostMapping("/enviar-email")
    ResponseEntity enviarEmail(EmailDTO emailDTO);
}
