package com.inovacao.senai.netero.servicos;

import com.inovacao.senai.netero.componentes.ValidadorEndereco;
import com.inovacao.senai.netero.enums.RoleEnum;
import com.inovacao.senai.netero.modelos.entidades.Empresa;
import com.inovacao.senai.netero.modelos.entidades.Role;
import com.inovacao.senai.netero.modelos.entidades.Telefone;
import com.inovacao.senai.netero.repositorios.EmpresaRepositorio;
import com.inovacao.senai.netero.repositorios.RoleRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;

@Service
public class EmpresaServico {

    @Autowired
    private EmpresaRepositorio empresaRepositorio;
    @Autowired
    private RoleRepositorio roleRepositorio;

    private ValidadorEndereco usuarioValidadorComponente;



    public void cadastrar(Empresa empresa) {
        empresa.setSenha(new BCryptPasswordEncoder().encode(empresa.getSenha()));
        var endereco = empresa.getEndereco();

        if (usuarioValidadorComponente.verificarAdequacaoEndereco(endereco)) {
            usuarioValidadorComponente.adequarEndereco(endereco);
        }

        for (Telefone telefone : empresa.getTelefones()) {
            telefone.setEmpresa(empresa);
        }
        endereco.setEmpresa(empresa);

        Role roles = roleRepositorio.findByIdentificador(RoleEnum.EMPRESA);
        empresa.setRoles(Collections.singletonList(roles));


        empresaRepositorio.save(empresa);
    }


}
