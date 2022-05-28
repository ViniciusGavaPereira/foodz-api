package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.example.demo.entity.Usuario;
//Esse daqui
public interface RepositorioUsuario extends JpaRepository<Usuario, Integer> {

    @Query("select u from Usuario u where login = ?1 and senha = ?2")
    Optional<Usuario> login(String email, String senha);

    @Query(nativeQuery = true, value = "select * from usuario where login=?1")
    public List<Usuario> buscarLogin(String login);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "delete from usuario where login =?1")
    public void excluirLogin(String login);
}
