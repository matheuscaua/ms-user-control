package com.inovacao.senai.netero.clients;

import com.inovacao.senai.netero.modelos.dtos.EmailDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "ms-email")
public interface EmailClient {
    @PostMapping("/enviar-email")
    ResponseEntity enviarEmail(EmailDTO emailDTO);
}
