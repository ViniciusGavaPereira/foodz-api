package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Produto;

public interface RepositorioProduto extends JpaRepository<Produto, Integer> {

    // Faz a seleção de um item com varios loes diferentes
    @Query(nativeQuery = true, value = "select * from estoque where Id_produto=?1")
    public List<Produto> trazerProduto(Integer idProduto);

    // Puxa pelo nome - ok
    Optional<Produto> findByProduto(String produto);

    // Seleciona o id que será o id do próximo produto - ok
    @Query(nativeQuery = true, value = "select max(Id_Produto) from estoque")
    public int findByIdProduto();

    // Busca tudo em ordem de Id - ok
    @Query(nativeQuery = true, value = "select * from estoque order by Id_produto;")
    public List<Produto> buscarOrdem();

    // Deleta mais de um item
    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "delete from estoque where Id_produto= ?1")
    public void excluirProduto(Integer idProduto);

   

}
