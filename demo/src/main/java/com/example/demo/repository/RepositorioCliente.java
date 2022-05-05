package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
import com.example.demo.entity.Cliente;

public interface RepositorioCliente extends JpaRepository<Cliente, Integer> {

}
