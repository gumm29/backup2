package com.loja.demo.models;

public class VerificaContaDTO {
  
  private String email;
  private String tokenEmail;

  public VerificaContaDTO(){}

  public String getEmail() { return email; }
  public void setEmail(String email) { this.email = email; }

  public String getTokenEmail() { return tokenEmail; }
  public void setTokenEmail(String tokenEmail) { this.tokenEmail = tokenEmail; }
}
