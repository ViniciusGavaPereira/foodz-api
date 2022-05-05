package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Usuario;
import com.example.demo.repository.RepositorioUsuario;


@RestController
@RequestMapping(path = "/usuario")
public class ControllerUsuario {

    @Autowired
    private RepositorioUsuario repositorioUsuario;
 
    @PostMapping(path = "/login")
    public Optional<Usuario> login(@RequestBody Usuario usuario) {
        return repositorioUsuario.login(usuario.getLogin(), usuario.getSenha());

    }


    //Arrumar 
    @GetMapping(path = "/buscarTudo")
    public List<Usuario> listarTodos() {
        return repositorioUsuario.findAll();

    }

    @GetMapping(path = "/{id}")
    public Optional<Usuario> buscaId(@PathVariable Integer id) {
        return repositorioUsuario.findById(id);
    }

    @PostMapping(path = "/")
    public Usuario salvarProduto(@RequestBody Usuario usuario) {
        return repositorioUsuario.save(usuario);

    }

    @PutMapping(path = "/{id}")
    public void alterarProduto(@PathVariable Integer id, @RequestBody Usuario usuario) {
        var resp = repositorioUsuario.findById(id);
        var usuarioAtual = resp.get();
        if (resp.isPresent()) {
            usuarioAtual.setNome(usuario.getNome());
            usuarioAtual.setSobrenome(usuario.getSobrenome());
            usuarioAtual.setLogin(usuario.getLogin());
            usuarioAtual.setSenha(usuario.getSenha());

            repositorioUsuario.save(usuarioAtual);

        } else {
            ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(path = "/{id}")
    public void deletarProduto(@PathVariable Integer id) {
        var resp = repositorioUsuario.findById(id);

        if (resp.isPresent()) {
            repositorioUsuario.deleteById(id);
        } else {
            ResponseEntity.notFound().build();
        }

    }


}
