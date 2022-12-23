package com.inovacao.senai.netero.servicos;


import com.inovacao.senai.netero.modelos.dto.UsuarioDTO;
import com.inovacao.senai.netero.modelos.entidades.Endereco;
import com.inovacao.senai.netero.modelos.entidades.Telefone;
import com.inovacao.senai.netero.modelos.entidades.Usuario;
import com.inovacao.senai.netero.repositorios.UsuarioRepositorio;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class UsuarioServico {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    public void cadastrar(UsuarioDTO usuarioDTO) {
        //Criptografa a senha
        usuarioDTO.setSenha(new BCryptPasswordEncoder().encode(usuarioDTO.getSenha()));

        Usuario usuarioEntidade = new Usuario();
        //Copia as propriedades do DTO para a Entidade
        BeanUtils.copyProperties(usuarioDTO, usuarioEntidade);

        //Seta usuario na Entidade Endereco
        var endereco = usuarioDTO.getEndereco();
        endereco.setUsuario(usuarioEntidade);

        //Seta usuario na Entidade Telefone
        for (Telefone telefone : usuarioDTO.getTelefones()){
            telefone.setUsuario(usuarioEntidade);
        }
        usuarioRepositorio.save(usuarioEntidade);

    }

    public List<Usuario> buscarNome(String nome) {
        return usuarioRepositorio.buscarUsuarioPorNome(nome);
    }

    public List<Usuario> buscarTodos() {
        return usuarioRepositorio.findAll();
    }

    public void deletar(String cpf, String email){
        var usuario = usuarioRepositorio.buscarUsuarioPorCpf(cpf);
        if(email.equals(usuario.getEmail())){
            usuarioRepositorio.deleteById(usuario.getId());
        }
    }
}
