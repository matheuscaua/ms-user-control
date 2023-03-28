package com.inovacao.senai.netero.clients;

import com.inovacao.senai.netero.modelos.dtos.EmpresaDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "ms-posts")
public interface PostsClient {

    @PostMapping("/posts/cadastrarEmpresa")
    ResponseEntity gravarEmpresa(EmpresaDTO empresa);

}
