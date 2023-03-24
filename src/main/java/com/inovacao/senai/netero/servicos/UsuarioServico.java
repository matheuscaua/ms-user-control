package com.inovacao.senai.netero.servicos;


import com.inovacao.senai.netero.clients.SegurancaClient;
import com.inovacao.senai.netero.enums.SituacaoEnum;
import com.inovacao.senai.netero.modelos.dtos.CredencialDTO;
import com.inovacao.senai.netero.modelos.entidades.Usuario;
import com.inovacao.senai.netero.repositorios.RoleRepositorio;
import com.inovacao.senai.netero.repositorios.UsuarioRepositorio;
import com.inovacao.senai.netero.enums.RoleEnum;
import com.inovacao.senai.netero.modelos.entidades.Role;
import com.inovacao.senai.netero.componentes.ValidadorEndereco;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
@Service
public class UsuarioServico {
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    @Autowired
    private RoleRepositorio roleRepositorio;
    @Autowired
    private SegurancaClient segurancaClient;
    public void cadastrar(Usuario usuario) throws Exception {
        try {
            usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
            usuario.getTelefones().stream().forEach(telefone -> telefone.setUsuario(usuario));
            setarEnderecoUsuario(usuario);
            setarAutorizacoes(usuario);
            usuario.setDataCadastro(new Date());
            usuario.setSituacao(SituacaoEnum.ATIVO);
            usuarioRepositorio.save(usuario);
            segurancaClient.cadastrarCredencial(new CredencialDTO(usuario.getEmail(), usuario.getSenha()));
        }catch (FeignException e){
            throw new Exception();
        }catch (NullPointerException e){
            throw new Exception();
        }catch (Exception e){
            e.getMessage();
        }
    }
    protected void setarEnderecoUsuario(Usuario usuario){
        ValidadorEndereco validadorEndereco = new ValidadorEndereco();
        var endereco = usuario.getEndereco();
        if (validadorEndereco.verificarEndereco(endereco)) validadorEndereco.adequarEndereco(endereco);
        endereco.setUsuario(usuario);
    }
    protected void setarAutorizacoes(Usuario usuario){
        Role role = roleRepositorio.findByIdentificador(RoleEnum.CANDIDATO);
        if(role == null) throw new NullPointerException();
        usuario.setRole(Collections.singletonList(role));
    }
    public List<Usuario> buscarNome(String nome) {
        List<Usuario> usuarios = usuarioRepositorio.buscarUsuarioPorNome(nome);
        if (usuarios != null && !usuarios.isEmpty()) return usuarios;
        throw new NullPointerException();
    }
    public List<Usuario> buscarTodos() {
        List<Usuario> usuarios = usuarioRepositorio.findAll();
        if (!usuarios.isEmpty()) return usuarios;
        throw new NullPointerException();
    }

    /*public void deletar(String cpf, String email) {
        var usuario = usuarioRepositorio.buscarUsuarioPorCpf(cpf);
        if (usuario != null) if (email.equals(usuario.getEmail())) usuarioRepositorio.deleteById(usuario.getId());
        throw new NullPointerException();
    }*/


}
