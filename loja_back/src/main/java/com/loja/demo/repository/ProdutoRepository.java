package com.loja.demo.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.loja.demo.entity.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, UUID>{
  Optional<Produto> findById(UUID id);
  Optional<Produto> findByUrlName(String urlName);
}