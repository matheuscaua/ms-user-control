package com.inovacao.senai.netero.controladores;

import com.inovacao.senai.netero.modelos.entidades.Empresa;
import com.inovacao.senai.netero.servicos.EmpresaServico;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/empresa")
public class EmpresaControlador {

    @Autowired
    private EmpresaServico empresaServico;

    @PostMapping("/cadastrar")
    public ResponseEntity<Empresa> cadastrar(@RequestBody Empresa empresa){
        empresaServico.cadastrar(empresa);
        return ResponseEntity.status(201).build();
    }

}
