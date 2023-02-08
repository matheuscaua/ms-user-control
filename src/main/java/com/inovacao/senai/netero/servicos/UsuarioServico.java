package com.inovacao.senai.netero.servicos;


import com.inovacao.senai.netero.modelos.entidades.Telefone;
import com.inovacao.senai.netero.modelos.entidades.Usuario;
import com.inovacao.senai.netero.servicos.repositorios.RoleRepositorio;
import com.inovacao.senai.netero.servicos.repositorios.UsuarioRepositorio;
import com.inovacao.senai.netero.enums.RoleEnum;
import com.inovacao.senai.netero.modelos.dto.UsuarioDTO;
import com.inovacao.senai.netero.modelos.entidades.Role;

import com.inovacao.senai.netero.servicos.component.UsuarioValidadorComponente;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UsuarioServico {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    
    @Autowired
    private RoleRepositorio roleRepositorio;
    
    private UsuarioValidadorComponente usuarioValidadorComponente = new UsuarioValidadorComponente();
    
    public void cadastrar(Usuario usuario){
        usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
        var endereco = usuario.getEndereco();
    
        if(usuarioValidadorComponente.verificarAdequacaoEndereco(endereco)){
            usuarioValidadorComponente.adequarEndereco(endereco);
        }

        for(Telefone telefone: usuario.getTelefones()){
            telefone.setUsuario(usuario);
        }
        endereco.setUsuario(usuario);

        Role roles = roleRepositorio.findByIdentificador(RoleEnum.CANDIDATO);
        usuario.setRoles(Collections.singletonList(roles));

        usuarioRepositorio.save(usuario);
    }

    public List<Usuario> buscarNome(String nome) {
        List<Usuario> usuarios = usuarioRepositorio.buscarUsuarioPorNome(nome);
        if(usuarios != null && !usuarios.isEmpty()){
            return usuarios;
        }
        throw new NullPointerException();
    }

    public List<UsuarioDTO> buscarTodosDTO() {
        List<Usuario> usuarios = usuarioRepositorio.findAll();
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        List<UsuarioDTO> usuarioDTOS = usuarios.stream().map(usuario -> {
            BeanUtils.copyProperties(usuario,usuarioDTO);
            return usuarioDTO;
        }).filter(Objects::nonNull).collect(Collectors.toList());
        return usuarioDTOS;
    }

    public List<Usuario> buscarTodos() {
        List<Usuario> usuarios = usuarioRepositorio.findAll();
        if(!usuarios.isEmpty()){
            return usuarios;
        }
        throw new NullPointerException();
    }

    public void deletar(String cpf, String email){
        var usuario = usuarioRepositorio.buscarUsuarioPorCpf(cpf);
        if(usuario != null) {
            if (email.equals(usuario.getEmail())) {
                usuarioRepositorio.deleteById(usuario.getId());
            }
        }
        throw new NullPointerException();
    }

    public UsuarioDTO buscarPorEmail(String email){
        var usuario = usuarioRepositorio.findByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException("Usuario não existe!")
        );
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        BeanUtils.copyProperties(usuario,usuarioDTO);
        return usuarioDTO;
    }

}
