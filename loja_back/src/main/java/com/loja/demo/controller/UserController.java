package com.loja.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.loja.demo.models.Usuario;
import com.loja.demo.service.UserService;

@RestController
@RequestMapping("/autor")
public class UserController {

  @Autowired
  UserService user;

  @GetMapping("/{email}")
  public ResponseEntity<Usuario> nomeEmail(@PathVariable String email) {
    return user.email(email);
  }
}
