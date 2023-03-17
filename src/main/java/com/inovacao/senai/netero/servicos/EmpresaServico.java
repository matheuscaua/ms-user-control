package com.inovacao.senai.netero.servicos;

import com.inovacao.senai.netero.clients.PostsClient;
import com.inovacao.senai.netero.componentes.ValidadorEndereco;
import com.inovacao.senai.netero.enums.RoleEnum;
import com.inovacao.senai.netero.enums.SituacaoEnum;
import com.inovacao.senai.netero.modelos.dtos.EmpresaDTO;
import com.inovacao.senai.netero.modelos.entidades.Empresa;
import com.inovacao.senai.netero.modelos.entidades.Endereco;
import com.inovacao.senai.netero.modelos.entidades.Role;
import com.inovacao.senai.netero.modelos.entidades.Telefone;
import com.inovacao.senai.netero.repositorios.EmpresaRepositorio;
import com.inovacao.senai.netero.repositorios.RoleRepositorio;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    private ValidadorEndereco usuarioValidadorComponente = new ValidadorEndereco();

    public void cadastrar(Empresa empresa) {
        setarEndereco(empresa);
        setarTelefone(empresa);
        setarAutorizacao(empresa);
        empresa.setSituacao(SituacaoEnum.ATIVO);
        empresa.setDataCadastro(new Date());
        empresaRepositorio.save(empresa);
        criarEmpresaPosts(empresa, new EmpresaDTO());
    }

    public void criarEmpresaPosts(Empresa empresa,EmpresaDTO empresaDTO){
        BeanUtils.copyProperties(empresa,empresaDTO);
        postsClient.gravarEmpresa(empresaDTO);

    }
    public void setarEndereco(Empresa empresa){
        var endereco = empresa.getEndereco();
        verificarEndereco(endereco);
        endereco.setEmpresa(empresa);
    }
    public void verificarEndereco(Endereco endereco){
        if (usuarioValidadorComponente.verificarAdequacaoEndereco(endereco)) {
            usuarioValidadorComponente.adequarEndereco(endereco);
        }
    }
    public void setarAutorizacao(Empresa empresa){
        Role role = roleRepositorio.findByIdentificador(RoleEnum.EMPRESA);
        if(role == null) throw new NullPointerException("Role inexistente");
        empresa.setRoles(Collections.singletonList(role));
    }
    public void setarTelefone(Empresa empresa){
        for (Telefone telefone : empresa.getTelefones()) {
            telefone.setEmpresa(empresa);
        }
    }

}
