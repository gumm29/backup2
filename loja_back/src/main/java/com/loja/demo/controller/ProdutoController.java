package com.loja.demo.controller;

import java.util.Optional;
import java.util.UUID;

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
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.loja.demo.entity.Produto;
import com.loja.demo.models.ProdutoDtoRequest;
import com.loja.demo.models.ProdutoDtoResponse;
import com.loja.demo.service.ProdutoService;

@RestController
@RequestMapping
public class ProdutoController {

  @Autowired
  ProdutoService produto;

  @GetMapping("/produtos")
  //@org.springframework.cache.annotation.Cacheable("teste")
  public Page<ProdutoDtoResponse> listaProdutos(@PageableDefault(sort = "id", direction = Direction.DESC, page = 0, size = 10) Pageable paginacao) {
    return produto.listaProdutos(paginacao);
  }

  @GetMapping("/produto/{id}")
  public ResponseEntity<ProdutoDtoResponse> listaProduto(@PathVariable UUID id) {
    return produto.listaProduto(id);
  }

  // @PostMapping("/artigo/imagem")
  // public byte[] mostraImagem(MultipartFile foto) throws IOException {
  //   byte[] data = foto.getBytes();
  //   return Base64.getEncoder().encode(data);
  // }

  @PostMapping("/produto")
  public ResponseEntity<Produto> salvaProduto(@RequestBody @Valid ProdutoDtoRequest request, UriComponentsBuilder uriBuilder, @RequestHeader (name="Authorization") String token) {
    return produto.salvaProduto(request, uriBuilder, token);
  }

  @PutMapping("/produto/{id}")
  @Transactional
  public ResponseEntity<Produto> updateProduto(@PathVariable UUID id, @RequestBody @Valid ProdutoDtoRequest produtoDTO, @RequestHeader (name="Authorization") String token) {
    return produto.updateProduto(id, produtoDTO, token);
  }

  @PatchMapping("/produto/{id}")
  @Transactional
  public ResponseEntity<Optional<Produto>> updatePublicacao(@PathVariable UUID id, @RequestParam boolean publicado, @RequestHeader (name="Authorization") String token) {
    return produto.updatePublicacao(id, publicado, token);
  }

  @DeleteMapping("/produto/{id}")
  @Transactional
  public ResponseEntity<Optional<Produto>> deletaProduto(@PathVariable UUID id, @RequestHeader (name="Authorization") String token) {
    return produto.deletaProduto(id, token);
  }
}