package com.loja.demo.models;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class Login {

  private String email;
  private String senha;
  private int perfil;

  public Login(){}

  public String getEmail(){ return email; }
  public void email(String email) { this.email = email; }

  public String getSenha(){ return senha; }
  public void senha(String senha) { this.senha = senha; }

  public int getPerfil() { return this.perfil; }
  public void setPerfil(int perfil) { this.perfil = perfil; }

  public UsernamePasswordAuthenticationToken covert() {
    return new UsernamePasswordAuthenticationToken(email, senha);
  }
}