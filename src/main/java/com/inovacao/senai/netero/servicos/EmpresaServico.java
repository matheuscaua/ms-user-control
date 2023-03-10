package com.inovacao.senai.netero.servicos;

import com.inovacao.senai.netero.componentes.ValidadorEndereco;
import com.inovacao.senai.netero.modelos.entidades.Empresa;
import com.inovacao.senai.netero.modelos.entidades.Telefone;
import com.inovacao.senai.netero.repositorios.EmpresaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class EmpresaServico {

    @Autowired
    private EmpresaRepositorio empresaRepositorio;

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


        /* TODO
         *   Implement set roles empresa */

        empresaRepositorio.save(empresa);
    }


}
