package com.inovacao.senai.netero.servicos;


import com.inovacao.senai.netero.modelos.dto.UsuarioDTO;
import com.inovacao.senai.netero.modelos.entidades.Usuario;
import com.inovacao.senai.netero.repositorios.UsuarioRepositorio;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServico {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    public void cadastrar(UsuarioDTO usuarioDTO) {
        usuarioDTO.setSenha(new BCryptPasswordEncoder().encode(usuarioDTO.getSenha()));
        Usuario usuarioEntidade = new Usuario();
        BeanUtils.copyProperties(usuarioDTO, usuarioEntidade);
        usuarioRepositorio.save(usuarioEntidade);
    }

    public List<Usuario> buscarNome(String nome) {
        return usuarioRepositorio.buscarUsuarioPorNome(nome);
    }

    public List<Usuario> buscarTodos() {
        return usuarioRepositorio.findAll();
    }


}
