package com.loja.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.loja.demo.models.Login;
import com.loja.demo.models.Token;
import com.loja.demo.service.AutenticacaoService;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController{

  @Autowired
  private AutenticacaoService auth;

  @Autowired
  private AuthenticationManager authManager;

  @PostMapping
  public ResponseEntity<Token> autenticar(@RequestBody @Valid Login login){
    return auth.autentica(login, authManager);
  }

  @PostMapping("/cliente")
  public ResponseEntity<Token> autenticaCliente(@RequestBody @Valid Login login){
    return auth.autenticaCLiente(login, authManager);
  }
}
