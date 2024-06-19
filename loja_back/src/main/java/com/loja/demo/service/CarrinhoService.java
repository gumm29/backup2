package com.loja.demo.service;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import com.loja.demo.config.FilterToken;
import com.loja.demo.entity.Carrinho;
import com.loja.demo.entity.Produto;
import com.loja.demo.entity.User;
import com.loja.demo.exception.NotFoundException;
import com.loja.demo.models.CarrinhoDtoRequest;
import com.loja.demo.models.CarrinhoDtoResponse;
import com.loja.demo.models.CarrinhoEditDto;
import com.loja.demo.models.CarrinhoIdDto;
import com.loja.demo.repository.CarrinhoRepository;
import com.loja.demo.repository.ProdutoRepository;
import com.loja.demo.repository.UsuarioRepository;

@Service
public class CarrinhoService {

  @Autowired
  CarrinhoRepository carrinhoRepository;

  @Autowired
  ProdutoRepository produtoRepository;

  @Autowired
  TokenService tokenService;

  @Autowired
  UsuarioRepository usuarioRepository;

  @Autowired
  AutenticacaoService autenticacaoServico;

  public Page<CarrinhoDtoResponse> listaProdutos(Pageable paginacao){
    Page<Carrinho> produto = carrinhoRepository.findAll(paginacao);
    return CarrinhoDtoResponse.converter(produto);
  }

  public ResponseEntity<List<CarrinhoDtoResponse>> listaProdutosCliente(UUID id, String token){
    User user = tokenService.autenticaClienteToken(token);
    List<Carrinho> produto = carrinhoRepository.findAllByClienteId(user.getId());
    List<CarrinhoDtoResponse> carrinho = CarrinhoDtoResponse.converter(produto);
    // System.out.println(carrinho.get());
    return ResponseEntity.ok(carrinho);
  }

  public ResponseEntity<CarrinhoIdDto> listaProduto(String token){
    User user = tokenService.autenticaClienteToken(token);
    List<Carrinho> selecionado = carrinhoRepository.findAllByClienteId(user.getId());
    // Optional<Carrinho> selecionado = carrinhoRepository.findByClienteId(user.getId());
    if(!selecionado.isEmpty()){
      Carrinho produto = selecionado.get(0);
      CarrinhoIdDto response = new CarrinhoIdDto(produto);
      return ResponseEntity.ok(response);
    }else{
      throw new NotFoundException("O produto não foi encontrado");
    }
  }

  public ResponseEntity<CarrinhoDtoResponse> salvaProduto(CarrinhoDtoRequest request, UriComponentsBuilder uriBuilder, String token){
    //if(autenticacaoServico.verificaUsuario(token).equals("1")){ return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); }
    Optional<Produto> produto = produtoRepository.findById(request.getProduto());
    User user = tokenService.autenticaClienteToken(token);
    if(produto.isPresent() ){
      Produto prod = produto.get();
      List<Carrinho> selecionado = carrinhoRepository.findAllByClienteId(user.getId());
      
      Optional<Carrinho> existeCarrinho = carrinhoRepository.findByClienteId(user.getId());
      if(existeCarrinho.isPresent()){
        selecionado.forEach(produ -> {
          if(produ.getProduto().toString().equals(prod.getId().toString())){
            CarrinhoDtoRequest.somaProduto(produ, carrinhoRepository, request.getQuantidade());
          }else{
            Carrinho carrinho = CarrinhoDtoRequest.converter(existeCarrinho.get().getCarrinhoId(), prod, user, request);
            carrinhoRepository.save(carrinho);
          }
        });
      }else{
        Carrinho carrinho = CarrinhoDtoRequest.converter(produto.get(), user, request);
        carrinhoRepository.save(carrinho);
      }
      Carrinho carrinhoGet = carrinhoRepository.findAllByClienteId(user.getId()).get(0);
      CarrinhoDtoResponse carrinhoResponse = CarrinhoDtoResponse.converterProduto(carrinhoGet.getId(), carrinhoRepository);
      URI uri = uriBuilder.path("/carrinho/{id}").buildAndExpand(carrinhoRepository.count()+1).toUri();
      return ResponseEntity.created(uri).body(carrinhoResponse);
    }else{
      return ResponseEntity.notFound().build();
    }
  }

  public ResponseEntity<CarrinhoDtoResponse> updateProduto(UUID id, CarrinhoEditDto request, String token) {
    //if(autenticacaoServico.verificaUsuario(token).equals("1")){ return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); }

    Optional<Carrinho> prod = carrinhoRepository.findByCarrinhoId(id);
    if(prod.isPresent()){
      Carrinho carrinho = request.atualizar(prod.get(), carrinhoRepository, request.getQuantidade());
      CarrinhoDtoResponse carrinhoResponse = CarrinhoDtoResponse.converterProduto(carrinho.getId(), carrinhoRepository);
      return ResponseEntity.ok(carrinhoResponse);
    }
    return ResponseEntity.notFound().build();
  }

  public ResponseEntity<CarrinhoDtoResponse> deletaProduto(UUID id, UUID produto,  String token) {
    //if(autenticacaoServico.verificaUsuario(token).equals("1")){ return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); }

    List<Carrinho> selecionado = carrinhoRepository.findAllByCarrinhoId(id);
    if (!selecionado.isEmpty()) {
      selecionado.forEach(produtoCarrinho -> {
        if(produtoCarrinho.getProduto().toString().equals(produto.toString())){
          carrinhoRepository.deleteById(produtoCarrinho.getId());
        }
      });
      return ResponseEntity.noContent().build();
    }
    return ResponseEntity.notFound().build();
  }
}
