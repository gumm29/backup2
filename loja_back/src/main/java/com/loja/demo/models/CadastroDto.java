package com.loja.demo.models;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

import com.loja.demo.entity.User;

public class CadastroDto {

  @NotEmpty
  @NotNull
  @Size(min = 3)
  private String nome;
  @NotEmpty
  @Email
  private String email;
  @NotEmpty
  @Length(min = 3)
  private String senha;

  public String getNome() { return nome; } 
  public String getEmail() { return email; }
  public String getSenha() { return senha; }

  public static User converter(CadastroDto cadastro){
    return new User(cadastro);
  }
}
