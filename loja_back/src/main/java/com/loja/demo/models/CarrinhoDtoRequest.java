package com.loja.demo.models;

import java.util.UUID;

import com.loja.demo.entity.Carrinho;
import com.loja.demo.entity.Produto;
import com.loja.demo.entity.User;
import com.loja.demo.repository.CarrinhoRepository;

public class CarrinhoDtoRequest {
  private UUID produto;
  private int quantidade;

  public UUID getProduto(){ return this.produto; }
  public void setProduto(UUID produto){ this.produto = produto; }

  public int getQuantidade(){ return this.quantidade; }
  public void setQuantidade(int quantidade){ this.quantidade = quantidade; }

  public static Carrinho converter(Produto produto, User user, CarrinhoDtoRequest carrinho){
    return new Carrinho(produto, user, carrinho);
  }

  public static Carrinho converter(UUID id, Produto produto, User user, CarrinhoDtoRequest carrinho){
    return new Carrinho(id, produto, user, carrinho);
  }

  public Carrinho atualizar(UUID id, CarrinhoRepository request){
    Carrinho produto = request.getOne(id);
    produto.setProduto(this.produto);
    //produto.setPreco(this.preco);
    produto.setQuantidade(this.quantidade);
    return produto;
  }

  public static void somaProduto(Carrinho id, CarrinhoRepository request, int quantidade){
    Carrinho produto = request.findByProdutoCarrinho(id.getProduto(), id.getCarrinhoId()).get();
    System.out.println("teste1 "+produto.getPreco());
    // produto.setProduto(produto);
    produto.setQuantidade(produto.getQuantidade() + quantidade);
    request.save(produto);
  }
}
