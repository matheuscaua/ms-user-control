package com.inovacao.senai.netero.repositorios;

import com.inovacao.senai.netero.modelos.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {

    @Query(value = "select * from tb_usuario where tb_usuario.nome LIKE %?1%", nativeQuery = true)
    List<Usuario> buscarUsuarioPorNome(String nome);




}
