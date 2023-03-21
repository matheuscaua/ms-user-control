package com.inovacao.senai.netero.servicos;


import com.inovacao.senai.netero.modelos.entidades.Usuario;
import com.inovacao.senai.netero.repositorios.RoleRepositorio;
import com.inovacao.senai.netero.repositorios.UsuarioRepositorio;
import com.inovacao.senai.netero.enums.RoleEnum;
import com.inovacao.senai.netero.modelos.entidades.Role;

import com.inovacao.senai.netero.componentes.ValidadorEndereco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UsuarioServico {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private RoleRepositorio roleRepositorio;

    private final ValidadorEndereco usuarioValidadorComponente = new ValidadorEndereco();

    public void cadastrar(Usuario usuario) {
        usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
        var endereco = usuario.getEndereco();
        endereco.setUsuario(usuario);

        usuario.getTelefones().stream().forEach(telefone -> telefone.setUsuario(usuario));

        if (usuarioValidadorComponente.verificarAdequacaoEndereco(endereco)) {
            usuarioValidadorComponente.adequarEndereco(endereco);
        }
        Role roles = roleRepositorio.findByIdentificador(RoleEnum.CANDIDATO);
        usuario.setRoles(Collections.singletonList(roles));
        usuarioRepositorio.save(usuario);
    }

    public List<Usuario> buscarNome(String nome) {
        List<Usuario> usuarios = usuarioRepositorio.buscarUsuarioPorNome(nome);
        if (usuarios != null && !usuarios.isEmpty()) {
            return usuarios;
        }
        throw new NullPointerException();
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


}
