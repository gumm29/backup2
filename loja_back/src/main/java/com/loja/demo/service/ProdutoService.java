package com.loja.demo.service;

import java.net.URI;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import com.loja.demo.entity.Produto;
import com.loja.demo.exception.NotFoundException;
import com.loja.demo.models.ProdutoDtoRequest;
import com.loja.demo.models.ProdutoDtoResponse;
import com.loja.demo.repository.ProdutoRepository;

@Service
public class ProdutoService {

  @Autowired
  ProdutoRepository produtoRepository;

  @Autowired
  AutenticacaoService autenticacaoServico;

  public Page<ProdutoDtoResponse> listaProdutos(Pageable paginacao){
    Page<Produto> produto = produtoRepository.findAll(paginacao);
    return ProdutoDtoResponse.converter(produto);
  }
  
  public ResponseEntity<ProdutoDtoResponse> listaProduto(UUID id){
    Optional<Produto> selecionado = produtoRepository.findById(id);
    if(selecionado.isPresent()){
      Produto produto = selecionado.get();
      ProdutoDtoResponse response = new ProdutoDtoResponse(produto);
      return ResponseEntity.ok(response);
    }else{
      throw new NotFoundException("O produto "+ id +" não foi encontrado");
    }
  }

  public ResponseEntity<Produto> salvaProduto(ProdutoDtoRequest request, UriComponentsBuilder uriBuilder, String token){
    //if(autenticacaoServico.verificaUsuario(token).equals("1")){ return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); }

    Produto produto = ProdutoDtoRequest.converter(request);
    produtoRepository.save(produto);
    URI uri = uriBuilder.path("/produto/{id}").buildAndExpand(produtoRepository.count()+1).toUri();
    return ResponseEntity.created(uri).body(produto);
  }

  public ResponseEntity<Produto> updateProduto(UUID id, ProdutoDtoRequest produtoDTO, String token) {
    //if(autenticacaoServico.verificaUsuario(token).equals("1")){ return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); }

    Optional<Produto> prod = produtoRepository.findById(id);
    if(prod.isPresent()){
      Produto produto = produtoDTO.atualizar(id, produtoRepository);
      return ResponseEntity.ok(produto);
    }
    return ResponseEntity.notFound().build();
  }

  public ResponseEntity<Optional<Produto>> updatePublicacao(UUID id, boolean publicado, String token) {
    //if(autenticacaoServico.verificaUsuario(token).equals("1")){ return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); }

    Optional<Produto> selecionado = produtoRepository.findById(id);
    if (selecionado.isPresent()) selecionado.get().setPublicado(publicado);
    return selecionado.isPresent() ? ResponseEntity.ok(selecionado) : ResponseEntity.notFound().build();
  }

  public ResponseEntity<Optional<Produto>> deletaProduto(UUID id, String token) {
    //if(autenticacaoServico.verificaUsuario(token).equals("1")){ return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); }

    Optional<Produto> selecionado = produtoRepository.findById(id);
    if (selecionado.isPresent()) {
      produtoRepository.deleteById(id);
      return ResponseEntity.noContent().build();
    }
    return ResponseEntity.notFound().build();
  }
}
