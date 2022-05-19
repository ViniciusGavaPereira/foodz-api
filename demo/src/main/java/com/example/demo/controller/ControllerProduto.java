package com.example.demo.controller;

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

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Produto;
import com.example.demo.repository.RepositorioProduto;

@RestController
@RequestMapping(path = "/produto")
public class ControllerProduto {

    @Autowired
    private RepositorioProduto repositorioProduto;

    @GetMapping(path = "/buscarTudo")
    public List<Produto> buscarOrdem() {
        return repositorioProduto.buscarOrdem();

    }

    @GetMapping(path = "/buscarNome")
    public List<Produto> findByProduto(String produto) {
        System.out.println(produto);
        return repositorioProduto.findByProduto(produto);

    }

    @GetMapping(path = "/buscarID")
    public int findIdProduto() {
        var resp = repositorioProduto.findByIdProduto();
        return resp + 1;

    }

    @GetMapping(path = "/{id}")
    public Optional<Produto> buscaId(@PathVariable Integer id) {
        return repositorioProduto.findById(id);
    }

    @PostMapping(path = "/")
    public Produto salvarProduto(@RequestBody Produto produto) {
        return repositorioProduto.save(produto);

    }

    // Mexa
    @PostMapping(path = "/novoLote")
    Optional<Produto> acrescentarLote(@RequestBody Produto produto) {
        return repositorioProduto.acrescentarLote(produto.getIdProduto(), produto.getProduto(), produto.getQuantidade(),
                produto.getValidade(),
                produto.getCusto(), produto.getPreco(), produto.getLote());

    }

    @PutMapping(path = "/{id}")
    public void alterarProduto(@PathVariable Integer id, @RequestBody Produto produto) {
        var resp = repositorioProduto.findById(id);
        var produtoAtual = resp.get();
        if (resp.isPresent()) {
            produtoAtual.setProduto(produto.getProduto());
            produtoAtual.setQuantidade(produto.getQuantidade());
            produtoAtual.setValidade(produto.getValidade());
            produtoAtual.setCusto(produto.getCusto());
            produtoAtual.setPreco(produto.getPreco());
            produtoAtual.setLote(produto.getLote());

            repositorioProduto.save(produtoAtual);

        } else {
            ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(path = "/{id}")
    public void deletarProduto(@PathVariable Integer id) {
        var resp = repositorioProduto.findById(id);

        if (resp.isPresent()) {
            repositorioProduto.deleteById(id);
        } else {
            ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping(path = "/{id}/{lote}")
    public void excluirIdLote(@PathVariable Integer id, @PathVariable String lote) {
        System.out.println(id);
        System.out.println(lote);
        repositorioProduto.excluirIdLote(id, lote);
    }

    // @GetMapping(path = "/buscarUltimoID")
    // public List<Produto> listarUltimoID()
    // return repositorioProduto.proximoId();

}
