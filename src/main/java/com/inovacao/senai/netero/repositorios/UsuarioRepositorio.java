package com.inovacao.senai.netero.repositorios;

import com.inovacao.senai.netero.modelos.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {

    @Query(value = "select * from tb_usuario where tb_usuario.nome LIKE %?1%", nativeQuery = true)
    List<Usuario> buscarUsuarioPorNome(String nome);

    @Query(value = "select * from tb_usuario where tb_usuario.cpf = ?1", nativeQuery = true)
    Usuario buscarUsuarioPorCpf(String cpf);

    Optional<Usuario> findByEmail(String username);


}
