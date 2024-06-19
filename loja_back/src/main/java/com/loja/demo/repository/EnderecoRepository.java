package com.loja.demo.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import com.loja.demo.entity.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, UUID>{
  Optional<Endereco> findByClienteId(UUID id);
}
