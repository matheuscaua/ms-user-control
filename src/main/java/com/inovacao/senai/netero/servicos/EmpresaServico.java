package com.inovacao.senai.netero.servicos;

import com.inovacao.senai.netero.clients.PostsClient;
import com.inovacao.senai.netero.componentes.ValidadorEndereco;
import com.inovacao.senai.netero.enums.RoleEnum;
import com.inovacao.senai.netero.enums.SituacaoEnum;
import com.inovacao.senai.netero.modelos.dtos.EmpresaDTO;
import com.inovacao.senai.netero.modelos.entidades.Empresa;
import com.inovacao.senai.netero.modelos.entidades.Role;
import com.inovacao.senai.netero.repositorios.EmpresaRepositorio;
import com.inovacao.senai.netero.repositorios.RoleRepositorio;
import feign.FeignException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.Date;
@Service
public class EmpresaServico {
    @Autowired
    private EmpresaRepositorio empresaRepositorio;
    @Autowired
    private RoleRepositorio roleRepositorio;
    @Autowired
    private PostsClient postsClient;

    public void cadastrar(Empresa empresa) {
        try {
            empresa.setSenha(new BCryptPasswordEncoder().encode(empresa.getSenha()));
            empresa.getTelefones().stream().forEach(telefone -> telefone.setEmpresa(empresa));
            setarEndereco(empresa);
            setarAutorizacao(empresa);
            empresa.setSituacao(SituacaoEnum.ATIVO);
            empresa.setDataCadastro(new Date());
            empresaRepositorio.save(empresa);
            criarEmpresaPosts(new EmpresaDTO(empresa.getId(), empresa.getNome(), empresa.getSituacao()));
        }catch (FeignException e){
            e.getMessage();
        }catch (NullPointerException e){
            e.getMessage();
        }catch (Exception e){
            e.getMessage();
        }
    }
    protected void setarEndereco(Empresa empresa){
        ValidadorEndereco validadorEndereco = new ValidadorEndereco();
        var endereco = empresa.getEndereco();
        if (validadorEndereco.verificarEndereco(endereco)) validadorEndereco.adequarEndereco(endereco);
        endereco.setEmpresa(empresa);
    }
    protected void setarAutorizacao(Empresa empresa){
        Role role = roleRepositorio.findByIdentificador(RoleEnum.EMPRESA);
        if(role == null) throw new NullPointerException("Role nula!");
        empresa.setRoles(Collections.singletonList(role));
    }
    public void criarEmpresaPosts(EmpresaDTO empresaDTO){
        postsClient.gravarEmpresa(empresaDTO);
    }

}
