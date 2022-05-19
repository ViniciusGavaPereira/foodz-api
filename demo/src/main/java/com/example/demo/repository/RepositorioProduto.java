package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
 

import com.example.demo.entity.Produto;

public interface RepositorioProduto extends JpaRepository<Produto, Integer> {

    // @Query("select Id_produto from estoque")
    // List<Produto> proximoId();

    // Puxa pelo nome - ok
    List<Produto> findByProduto(String produto);

    // Seleciona o id que será o id do próximo produto - ok
    @Query(nativeQuery = true, value = "select max(Id_Produto) from estoque")
    public int findByIdProduto();

    // Busca tudo em ordem de Id - ok
    @Query(nativeQuery = true, value = "select * from estoque order by Id_produto;")
    List<Produto> buscarOrdem();

    // Excluir arquivo por id e lote:
    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "delete from estoque where Id_produto= ?1 and Lote = ?2")
    int excluirIdLote(Integer idProduto, String lote);

    // Teste
    @Query(nativeQuery = true, value = "insert into estoque(Id_produto, Produto, Quantidade, Validade, Custo, Preco, Lote) values(?1, ?2, ?3, ?4, ?5, ?6, ?7);")
    Optional<Produto> acrescentarLote(Integer idProduto, String produto, int quantidade, Date validade,
            double custo, double preco, String lote);

}
