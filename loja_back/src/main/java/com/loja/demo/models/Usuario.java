package com.loja.demo.models;

import com.loja.demo.entity.User;

public class Usuario {

  String nome;
  String email;

  public Usuario(User user){
    this.nome = user.getNome();
    this.email = user.getEmail();
  }

  public String getNome() { return this.nome; }
  public void setNome(String nome) { this.nome = nome; }

  public String getEmail() { return this.email; }
  public void setEmail(String email) { this.email = email; }
}
