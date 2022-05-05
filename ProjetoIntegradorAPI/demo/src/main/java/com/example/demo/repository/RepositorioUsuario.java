package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

import com.example.demo.entity.Usuario;

public interface RepositorioUsuario extends JpaRepository<Usuario, Integer> {

    @Query("select u from Usuario u where login = ?1 and senha = ?2")
    Optional<Usuario> login(String email, String senha);
    
}
