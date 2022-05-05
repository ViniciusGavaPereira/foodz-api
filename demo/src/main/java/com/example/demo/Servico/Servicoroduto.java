package com.example.demo.Servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Produto;
import com.example.demo.repository.RepositorioProduto;

@Service
public class Servicoroduto {

    @Autowired
    private RepositorioProduto repositorioProduto;

    public List<Produto> listarTodos() {

        return repositorioProduto.findAll();

    }

    public Optional<Produto> buscarPorId(Integer idProduto) {

        return repositorioProduto.findById(idProduto);
    }
}
