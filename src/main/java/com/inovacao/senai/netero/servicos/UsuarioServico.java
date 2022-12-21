package com.inovacao.senai.netero.servicos;


import com.inovacao.senai.netero.modelos.dto.UsuarioDTO;
import com.inovacao.senai.netero.modelos.entidades.Usuario;
import com.inovacao.senai.netero.repositorios.UsuarioRepositorio;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServico {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    public void cadastrar(UsuarioDTO usuarioDTO) {
        Usuario usuarioEntidade = new Usuario();
        BeanUtils.copyProperties(usuarioDTO, usuarioEntidade);
        usuarioRepositorio.save(usuarioEntidade);
    }



}
