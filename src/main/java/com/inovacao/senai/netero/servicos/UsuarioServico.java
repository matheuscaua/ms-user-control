package com.inovacao.senai.netero.servicos;


import com.inovacao.senai.netero.enums.RoleEnum;
import com.inovacao.senai.netero.modelos.entidades.Role;
import com.inovacao.senai.netero.modelos.entidades.Usuario;
import com.inovacao.senai.netero.repositorios.ParametroRepositorio;
import com.inovacao.senai.netero.repositorios.UsuarioRepositorio;
import com.inovacao.senai.netero.modelos.dto.UsuarioDTO;
import com.inovacao.senai.netero.modelos.dto.ViaCepDTO;
import com.inovacao.senai.netero.modelos.entidades.Endereco;
import com.inovacao.senai.netero.modelos.entidades.Telefone;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Transactional
@Service
public class UsuarioServico {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private ViaCepServico viaCepServico;

    @Autowired
    private ParametroRepositorio parametroRepositorio;


    private void settup(){
        
    }


    public void cadastrar(UsuarioDTO usuarioDTO) {
        if(usuarioDTO != null) {
            Usuario usuarioEntidade = new Usuario();
            usuarioDTO.setSenha(new BCryptPasswordEncoder().encode(usuarioDTO.getSenha()));
            var endereco = usuarioDTO.getEndereco();
            var dadosViaCep = viaCepServico.buscarDadosViaCep(endereco.getCep());
            endereco.setUsuario(usuarioEntidade);
            for (Telefone telefone : usuarioDTO.getTelefones()) {
                telefone.setUsuario(usuarioEntidade);
            }
            BeanUtils.copyProperties(usuarioDTO, usuarioEntidade);
            adequarEndereco(dadosViaCep, endereco);
            usuarioRepositorio.save(usuarioEntidade);
        }
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

    public void adequarEndereco(ViaCepDTO viaCepDTO, Endereco endereco){
        if(viaCepDTO != null  && viaCepDTO.getLogradouro() != null){
            if(!endereco.getBairro().equals(viaCepDTO.getBairro()) || endereco.getBairro().isEmpty()){
                endereco.setBairro(viaCepDTO.getBairro());
            }
            if(!endereco.getLogradouro().equals(viaCepDTO.getLogradouro())){
                endereco.setLogradouro(viaCepDTO.getLogradouro());
            }
            if(!endereco.getLocalidade().equals(viaCepDTO.getLocalidade())){
                endereco.setLocalidade(viaCepDTO.getLocalidade());
            }
            if(!endereco.getUf().equals(viaCepDTO.getUf())){
                endereco.setUf(viaCepDTO.getUf());
            }
        }
    }
}
