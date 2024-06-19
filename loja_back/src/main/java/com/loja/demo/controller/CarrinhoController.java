package com.loja.demo.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.loja.demo.models.CarrinhoDtoRequest;
import com.loja.demo.models.CarrinhoDtoResponse;
import com.loja.demo.models.CarrinhoEditDto;
import com.loja.demo.models.CarrinhoIdDto;
import com.loja.demo.service.CarrinhoService;

@RestController
@RequestMapping
public class CarrinhoController {

  @Autowired
  CarrinhoService carrinho;

  @GetMapping("/carrinho")
  public Page<CarrinhoDtoResponse> listaProdutos(@PageableDefault(sort = "id", direction = Direction.DESC, page = 0, size = 10) Pageable paginacao) {
    return carrinho.listaProdutos(paginacao);
  }

  @GetMapping("/carrinho/{id}")
  public ResponseEntity<List<CarrinhoDtoResponse>> listaProdutos(@PathVariable UUID id, @RequestHeader (name="Authorization") String token) {
    return carrinho.listaProdutosCliente(id, token);
  }

  @GetMapping("/carrinho/cliente")
  public ResponseEntity<CarrinhoIdDto> listaProduto(@RequestHeader (name="Authorization") String token) {
    return carrinho.listaProduto(token);
  }

  // @PostMapping("/artigo/imagem")
  // public byte[] mostraImagem(MultipartFile foto) throws IOException {
  //   byte[] data = foto.getBytes();
  //   return Base64.getEncoder().encode(data);
  // }

  @PostMapping("/carrinho")
  public ResponseEntity<CarrinhoDtoResponse> salvaProduto(@RequestBody @Valid CarrinhoDtoRequest request, UriComponentsBuilder uriBuilder, @RequestHeader (name="Authorization") String token) {
    return carrinho.salvaProduto(request, uriBuilder, token);
  }

  @PutMapping("/carrinho/{id}")
  @Transactional
  public ResponseEntity<CarrinhoDtoResponse> updateProduto(@PathVariable UUID id, @RequestBody @Valid CarrinhoEditDto request, @RequestHeader (name="Authorization") String token) {
    return carrinho.updateProduto(id, request, token);
  }

  @DeleteMapping("/carrinho/{id}/produto/{produto}")
  @Transactional
  public ResponseEntity<CarrinhoDtoResponse> deletaProduto(@PathVariable UUID id,@PathVariable UUID produto, @RequestHeader (name="Authorization") String token) {
    return carrinho.deletaProduto(id, produto, token);
  }
}