package com.loja.demo.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.loja.demo.entity.Carrinho;

public interface CarrinhoRepository extends JpaRepository<Carrinho, UUID>{
  Optional<Carrinho> findById(UUID id);
  Optional<Carrinho> findByClienteId(UUID id);
  List<Carrinho> findAllByClienteId(UUID id);
  List<Carrinho> findAllByCarrinhoId(UUID id);
  Optional<Carrinho> findByCarrinhoId(UUID id);
  Optional<Carrinho> findByProduto(UUID id);

  @Query("FROM Carrinho where produto = :produto and  carrinho_id = :id")
  Optional<Carrinho> findByProdutoCarrinho(@Param("produto") UUID produto,@Param("id") UUID id);
}