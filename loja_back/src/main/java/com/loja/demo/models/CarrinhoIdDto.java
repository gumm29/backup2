package com.loja.demo.models;

import java.util.UUID;

import com.loja.demo.entity.Carrinho;

public class CarrinhoIdDto {
  private UUID idCarrinhoCliente;

  public CarrinhoIdDto(){}

  public CarrinhoIdDto(Carrinho carrinho){
    this.idCarrinhoCliente = carrinho.getCarrinhoId();
  }

  public UUID getIdCarrinhoCliente(){ return this.idCarrinhoCliente; }
  public void setIdCarrinhoCliente(UUID idCarrinhoCliente){ this.idCarrinhoCliente = idCarrinhoCliente; }
}
