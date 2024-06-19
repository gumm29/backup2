package com.loja.demo.models;

public class Token {

  private String hash;
  private String tipo;

  public Token(String hash, String tipo){
    this.hash = hash;
    this.tipo = tipo;
  }

  public String getHash() { return this.hash; }
  public void setHash(String hash) { this.hash = hash; }

  public String getTipo() {return this.tipo; }
  public void setTipo(String tipo) { this.tipo = tipo; }
}
