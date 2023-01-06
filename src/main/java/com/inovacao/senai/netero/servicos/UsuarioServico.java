package com.inovacao.senai.netero.servicos;


import com.inovacao.senai.netero.repositorios.UsuarioRepositorio;
import com.inovacao.senai.netero.modelos.dto.UsuarioDTO;
import com.inovacao.senai.netero.modelos.dto.ViaCepDTO;
import com.inovacao.senai.netero.modelos.entidades.Endereco;
import com.inovacao.senai.netero.modelos.entidades.Telefone;
import com.inovacao.senai.netero.modelos.entidades.Usuario;
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

    @Autowired
    private ViaCepServico viaCepServico;

    public void cadastrar(UsuarioDTO usuarioDTO) {
        Usuario usuarioEntidade = new Usuario();
        usuarioDTO.setSenha(new BCryptPasswordEncoder().encode(usuarioDTO.getSenha()));

        var endereco = usuarioDTO.getEndereco();

        var dadosViaCep = viaCepServico.buscarDadosViaCep(endereco.getCep());
        endereco.setUsuario(usuarioEntidade);

        for (Telefone telefone : usuarioDTO.getTelefones()){
            telefone.setUsuario(usuarioEntidade);
        }
        BeanUtils.copyProperties(usuarioDTO, usuarioEntidade);

        adequarEndereco(dadosViaCep, endereco);

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
