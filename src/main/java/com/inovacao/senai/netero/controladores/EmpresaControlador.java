package com.inovacao.senai.netero.controladores;

import com.inovacao.senai.netero.modelos.entidades.Empresa;
import com.inovacao.senai.netero.servicos.EmpresaServico;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/empresa")
public class EmpresaControlador {

    @Autowired
    private EmpresaServico empresaServico;

    @PostMapping
    public ResponseEntity<Empresa> cadastrar(Empresa empresa){
        empresaServico.cadastrar(empresa);
        return ResponseEntity.status(201).build();
    }

}
