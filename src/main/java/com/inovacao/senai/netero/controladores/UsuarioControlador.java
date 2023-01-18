package com.inovacao.senai.netero.controladores;

import com.inovacao.senai.netero.modelos.dto.UsuarioDTO;
import com.inovacao.senai.netero.servicos.UsuarioServico;
import com.inovacao.senai.netero.servicos.ViaCepServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

    @RestController
    @RequestMapping("/usuario")
    public class UsuarioControlador {

        @Autowired
        private UsuarioServico usuarioServico;

        @Autowired
        private ViaCepServico viaCepServico;


        @PostMapping("/cadastrar")
        public ResponseEntity cadastrar(@RequestBody @Validated UsuarioDTO usuarioDTO) {
            usuarioServico.cadastrar(usuarioDTO);
            return ResponseEntity.status(201).build();
        }

        @GetMapping("/")
        public ResponseEntity buscarTodos() {
            return ResponseEntity.status(200).body(usuarioServico.buscarTodos());
        }


        @GetMapping("/{nome}")
        public ResponseEntity buscarPorNome(@PathVariable String nome) {
            return ResponseEntity.status(200).body(usuarioServico.buscarNome(nome));
        }

        @DeleteMapping("/{cpf}/{email}")
        public ResponseEntity deletarUsuario(@PathVariable String cpf, @PathVariable String email){
            usuarioServico.deletar(cpf, email);
            return ResponseEntity.status(200).build();
        }

        @GetMapping("/viacep/{cep}")
        public ResponseEntity testeViaCep(@PathVariable String cep) {
            return ResponseEntity.status(200).body(viaCepServico.buscarDadosViaCep(cep));
        }
}
