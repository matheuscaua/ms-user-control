package com.inovacao.senai.netero.controladores;

import com.inovacao.senai.netero.modelos.dto.UsuarioDTO;
import com.inovacao.senai.netero.servicos.UsuarioServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
public class UsuarioControlador {

    @Autowired
    private UsuarioServico usuarioServico;

    @PostMapping("/cadastrar")
    public ResponseEntity cadastrar(@RequestBody @Validated UsuarioDTO usuarioDTO) {
        usuarioServico.cadastrar(usuarioDTO);
        return ResponseEntity.status(201).build();
    }


    @GetMapping("/{nome}")
    public ResponseEntity buscarNome(@PathVariable  String nome) {
        return ResponseEntity.status(200).body(usuarioServico.buscarNome(nome));
    }

    @GetMapping("/buscarTodos")
    public ResponseEntity buscarTodos() {
        return ResponseEntity.status(200).body(usuarioServico.buscarTodos());
    }

}


