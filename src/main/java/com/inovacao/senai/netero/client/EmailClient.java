package com.inovacao.senai.netero.client;

import com.inovacao.senai.netero.modelos.dto.EmailDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "email", url = "http://MS-EMAIL")
public interface EmailClient {
    @PostMapping("/enviar-email")
    ResponseEntity enviarEmail(EmailDTO emailDTO);
}
