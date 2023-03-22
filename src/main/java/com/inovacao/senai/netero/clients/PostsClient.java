package com.inovacao.senai.netero.clients;

import com.inovacao.senai.netero.modelos.dtos.EmpresaDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "posts", url = "http://localhost:8400/")
public interface PostsClient {

    @PostMapping("/posts/criarEmpresa")
    ResponseEntity gravarEmpresa(EmpresaDTO empresa);

}
