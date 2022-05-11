package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Produto;

public interface RepositorioProduto extends JpaRepository<Produto, Integer> {

    // @Query("select Id_produto from estoque")
    // List<Produto> proximoId();

    List<Produto> findByProduto(String produto);

    @Query(nativeQuery = true, value = "select max(Id_Produto) from estoque")
    public int findByIdProduto();
}
