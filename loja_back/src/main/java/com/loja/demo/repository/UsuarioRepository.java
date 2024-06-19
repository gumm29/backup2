package com.loja.demo.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.loja.demo.entity.User;

public interface UsuarioRepository extends JpaRepository<User, Long> {
  Optional<User> findByEmail(String email);
  Optional<User> findById(UUID id);
}
