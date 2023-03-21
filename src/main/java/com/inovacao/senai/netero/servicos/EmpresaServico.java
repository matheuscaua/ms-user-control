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
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

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
        var endereco = empresa.getEndereco();
        endereco.setEmpresa(empresa);
        empresa.getTelefones().stream().forEach(telefone -> telefone.setEmpresa(empresa));
        if (usuarioValidadorComponente.verificarAdequacaoEndereco(endereco)) {
            usuarioValidadorComponente.adequarEndereco(endereco);
        }
        Role roles = roleRepositorio.findByIdentificador(RoleEnum.EMPRESA);
        empresa.setRoles(Collections.singletonList(roles));
        empresa.setSituacao(SituacaoEnum.ATIVO);
        empresaRepositorio.save(empresa);
    }

    public void criarEmpresaPosts(Empresa empresa,EmpresaDTO empresaDTO){
        BeanUtils.copyProperties(empresa,empresaDTO);
        postsClient.gravarEmpresa(empresaDTO);
    }

}
