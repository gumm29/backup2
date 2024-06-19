package com.loja.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.loja.demo.entity.User;
import com.loja.demo.models.Usuario;
import com.loja.demo.repository.UsuarioRepository;

@Service
public class UserService {

  @Autowired
  UsuarioRepository usuarioRepository;

  public ResponseEntity<Usuario> email(String email){
    Optional<User> emailUser = usuarioRepository.findByEmail(email);
    if(emailUser.isPresent()) return ResponseEntity.ok(new Usuario(emailUser.get()));
    return ResponseEntity.notFound().build();
  }
}
