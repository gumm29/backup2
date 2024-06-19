package com.loja.demo.models;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

import com.loja.demo.entity.Carrinho;
import com.loja.demo.repository.CarrinhoRepository;

public class CarrinhoDtoResponse {
  private UUID produto;
  private int quantidade;
  private double preco;

  public CarrinhoDtoResponse(){}

  public CarrinhoDtoResponse(Carrinho carrinho){
    this.produto = carrinho.getProduto();
    this.quantidade = carrinho.getQuantidade();
    this.preco = carrinho.getPreco();
  }

  public UUID getProduto(){ return this.produto; }
  public void setProduto(UUID produto){ this.produto = produto; }

  public int getQuantidade(){ return this.quantidade; }
  public void setQuantidade(int quantidade){ this.quantidade = quantidade; }

  public double getPreco(){ return this.preco; }
  public void setPreco(double preco){ this.preco = preco; }

  public static Page<CarrinhoDtoResponse> converter(Page<Carrinho> carrinho){
    return carrinho.map(CarrinhoDtoResponse::new);
  }

  public static List<CarrinhoDtoResponse> converter(List<Carrinho> carrinho){
    List<CarrinhoDtoResponse> response = new ArrayList<>();
    carrinho.forEach(e-> response.add(new CarrinhoDtoResponse(e)));
    return response;
    //.map(produto -> {new CarrinhoDtoResponse(produto)});
  }

  public static CarrinhoDtoResponse converterProduto(UUID id, CarrinhoRepository request){
    Carrinho carrinho = request.findById(id).get();
    return new CarrinhoDtoResponse(carrinho);
  }
}
