package com.inovacao.senai.netero.controladores;

import com.inovacao.senai.netero.modelos.entidades.Usuario;
import com.inovacao.senai.netero.servicos.UsuarioServico;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/usuario")
@CrossOrigin("*")
public class UsuarioControlador {

    @Autowired
    private UsuarioServico usuarioServico;


    @PostMapping("/cadastrar")
    public ResponseEntity cadastrar(@RequestBody @Validated Usuario usuario) {
        try {
            usuarioServico.cadastrar(usuario);
            return ResponseEntity.status(201).build();
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Erro ao cadastrar usuário!");
        }
    }

    @GetMapping("/")
    public ResponseEntity buscarTodos() {
        try {
            return ResponseEntity.status(200).body(usuarioServico.buscarTodos());
        } catch (NullPointerException e) {
            e.getMessage();
            return ResponseEntity.status(204).build();
        }
    }

    @GetMapping("/{nome}")
    public ResponseEntity buscarPorNome(@PathVariable String nome) {
        try {
            return ResponseEntity.status(200).body(usuarioServico.buscarNome(nome));
        } catch (NullPointerException e) {
            e.getMessage();
            return ResponseEntity.status(204).build();
        }
    }

   @DeleteMapping("/{cpf}/{email}")
    public ResponseEntity deletarUsuario(@PathVariable String cpf, @PathVariable String email) {
        try {
            usuarioServico.deletar(cpf, email);
            return ResponseEntity.status(200).build();
        } catch (NullPointerException e) {
            return ResponseEntity.status(204).build();
        }
    }

}
