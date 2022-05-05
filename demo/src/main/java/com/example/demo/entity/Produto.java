package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "estoque")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_produto")
    private Integer idProduto;

    @Column(name = "Produto")
    private String produto;

    @Column(name = "Quantidade")
    private int quantidade;

    @Column(name = "Validade")
    private String validade;

    @Column(name = "Custo")
    private double custo;

    @Column(name = "Preco")
    private double preco;

    @Column(name = "Lote")
    private String lote;


}
