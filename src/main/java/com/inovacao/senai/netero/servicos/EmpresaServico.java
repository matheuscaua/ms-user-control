package com.inovacao.senai.netero.servicos;

import com.inovacao.senai.netero.enums.RoleEnum;
import com.inovacao.senai.netero.modelos.entidades.*;
import com.inovacao.senai.netero.servicos.repositorios.EmpresaRepositorio;
import com.inovacao.senai.netero.servicos.component.UsuarioValidadorComponente;
import com.inovacao.senai.netero.servicos.repositorios.RoleRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class EmpresaServico {

    @Autowired
    private EmpresaRepositorio empresaRepositorio;

    @Autowired
    private UsuarioValidadorComponente usuarioValidadorComponente;


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

        /* TODO
        *   Implement set roles empresa */

        empresaRepositorio.save(empresa);
    }



}
