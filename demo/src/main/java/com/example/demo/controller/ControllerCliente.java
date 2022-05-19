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

import com.example.demo.entity.Cliente;
import com.example.demo.repository.RepositorioCliente;

@RestController
@RequestMapping(path = "/cliente")
public class ControllerCliente {

    @Autowired
    private RepositorioCliente repositorioCliente;

    
    @GetMapping(value = "/olaMundo")
    public String getMethodName() {
        return "Olá mundo";

    }

    @GetMapping(path = "/buscarTudo")
    public List<Cliente> listarTodos() {
        return repositorioCliente.findAll();

    }

    @GetMapping(path = "/{id}")
    public Optional<Cliente> buscaId(@PathVariable Integer id) {
        return repositorioCliente.findById(id);
    }

    @PostMapping(path = "/")
    public Cliente salvarCliente(@RequestBody Cliente cliente) {
        return repositorioCliente.save(cliente);

    }

    @PutMapping(path = "/{id}")
    public void alterarCliente(@PathVariable Integer id, @RequestBody Cliente cliente) {
        var resp = repositorioCliente.findById(id);
        var produtoAtual = resp.get();
        if (resp.isPresent()) {
            produtoAtual.setNome(cliente.getNome());
            produtoAtual.setEmail(cliente.getEmail());
            produtoAtual.setCpf(cliente.getCpf());
            produtoAtual.setTelefone(cliente.getTelefone());
            produtoAtual.setSenha(cliente.getSenha());
            
            repositorioCliente.save(produtoAtual);

        } else {
            ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(path = "/{id}")
    public void deletarCliente(@PathVariable Integer id) {
        var resp = repositorioCliente.findById(id);

        if (resp.isPresent()) {
            repositorioCliente.deleteById(id);
        } else {
            ResponseEntity.notFound().build();
        }

    }
}
