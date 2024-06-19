package com.loja.demo.models;

import org.springframework.data.domain.Page;

import com.loja.demo.entity.User;

public class ListaCadatrosDto {

  private String nome;
  private String email;

  public ListaCadatrosDto(){}

  public ListaCadatrosDto(User usuario){
    this.nome = usuario.getNome();
    this.email = usuario.getEmail();
  }

  public String getNome() { return nome; }
  public String getEmail() { return email; }

  public static Page<ListaCadatrosDto> converter(Page<User> cadastro){
    return cadastro.map(ListaCadatrosDto::new);
  }
}
