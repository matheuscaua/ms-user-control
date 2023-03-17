package com.inovacao.senai.netero.servicos;


import com.inovacao.senai.netero.clients.SegurancaClient;
import com.inovacao.senai.netero.enums.SituacaoEnum;
import com.inovacao.senai.netero.modelos.dtos.CredencialDTO;
import com.inovacao.senai.netero.modelos.entidades.Endereco;
import com.inovacao.senai.netero.modelos.entidades.Telefone;
import com.inovacao.senai.netero.modelos.entidades.Usuario;
import com.inovacao.senai.netero.repositorios.RoleRepositorio;
import com.inovacao.senai.netero.repositorios.UsuarioRepositorio;
import com.inovacao.senai.netero.enums.RoleEnum;
import com.inovacao.senai.netero.modelos.dtos.UsuarioDTO;
import com.inovacao.senai.netero.modelos.entidades.Role;

import com.inovacao.senai.netero.componentes.ValidadorEndereco;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UsuarioServico {

    private final UsuarioRepositorio usuarioRepositorio;

    private final RoleRepositorio roleRepositorio;

    private final ValidadorEndereco usuarioValidadorComponente;

    private final SegurancaClient segurancaClient;

    public void cadastrar(Usuario usuario) {
        usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
        setarTelefone(usuario);
        setarEndereco(usuario);
        setarAutorizacao(usuario);
        usuario.setSituacao(SituacaoEnum.ATIVO);
        usuario.setDataCadastro(new Date());
        cadastrarCrendencial(usuario);
        usuarioRepositorio.save(usuario);
    }

    public void verificarEndereco(Endereco endereco){
        if (usuarioValidadorComponente.verificarAdequacaoEndereco(endereco)) {
            usuarioValidadorComponente.adequarEndereco(endereco);
        }
    }

    public void setarEndereco(Usuario usuario){
        var endereco = usuario.getEndereco();
        verificarEndereco(endereco);
        endereco.setUsuario(usuario);
    }

    public void setarTelefone(Usuario usuario){
        for (Telefone telefone : usuario.getTelefones()) {
            telefone.setUsuario(usuario);
        }
    }

    public void setarAutorizacao(Usuario usuario){
        Role roles = roleRepositorio.findByIdentificador(RoleEnum.CANDIDATO);
        usuario.setRoles(Collections.singletonList(roles));
    }

    public List<Usuario> buscarNome(String nome) {
        List<Usuario> usuarios = usuarioRepositorio.buscarUsuarioPorNome(nome);
        if (usuarios != null && !usuarios.isEmpty()) {
            return usuarios;
        }
        throw new NullPointerException();
    }

    public List<UsuarioDTO> buscarTodosDTO() {
        List<Usuario> usuarios = usuarioRepositorio.findAll();
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        List<UsuarioDTO> usuarioDTOS = usuarios.stream().map(usuario -> {
            BeanUtils.copyProperties(usuario, usuarioDTO);
            return usuarioDTO;
        }).filter(Objects::nonNull).collect(Collectors.toList());
        return usuarioDTOS;
    }

    public List<Usuario> buscarTodos() {
        List<Usuario> usuarios = usuarioRepositorio.findAll();
        if (!usuarios.isEmpty()) {
            return usuarios;
        }
        throw new NullPointerException();
    }

    public void deletar(String cpf, String email) {
        var usuario = usuarioRepositorio.buscarUsuarioPorCpf(cpf);
        if (usuario != null) {
            if (email.equals(usuario.getEmail())) {
                usuarioRepositorio.deleteById(usuario.getId());
            }
        }
        throw new NullPointerException();
    }

    public CredencialDTO buscarPorEmail(String email) {
        var usuario = usuarioRepositorio.findByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException("Usuario não existe!")
        );
        CredencialDTO autenticacaoDTO = new CredencialDTO();
        BeanUtils.copyProperties(usuario, autenticacaoDTO);
        return autenticacaoDTO;
    }
    public void cadastrarCrendencial(Usuario usuario){
        segurancaClient.cadastrarCredencial(
                new CredencialDTO(usuario.getEmail(),usuario.getSenha(), usuario.getRoles())
        );

    }
}
