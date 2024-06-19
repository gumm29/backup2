package com.loja.demo.controller;

import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.loja.demo.entity.Endereco;
import com.loja.demo.models.EnderecoDtoRequest;
import com.loja.demo.service.EnderecoService;

@RestController
@RequestMapping("/cliente")
public class EnderecoController {

  @Autowired
  EnderecoService enderecoService;

  @GetMapping("/endereco")
  public ResponseEntity<Endereco> listaEndereco(@RequestHeader (name="Authorization") String token) {
    return enderecoService.listaEndereco(token);
  }

  @PostMapping("/endereco")
  public ResponseEntity<Endereco> salvaEndereco(@RequestBody @Valid EnderecoDtoRequest request, UriComponentsBuilder uriBuilder, @RequestHeader (name="Authorization") String token) {
    return enderecoService.salvaEndereco(request, uriBuilder, token);
  }

  @PutMapping("/endereco/{id}")
  @Transactional
  public ResponseEntity<Endereco> updateEndereco(@PathVariable UUID id, @RequestBody @Valid EnderecoDtoRequest request, @RequestHeader (name="Authorization") String token) {
    return enderecoService.updateEndereco(id, request, token);
  }

  @DeleteMapping("/endereco/{id}")
  @Transactional
  public ResponseEntity<Optional<Endereco>> deletaEndereco(@PathVariable UUID id, @RequestHeader (name="Authorization") String token) {
    return enderecoService.deletaEndereco(id, token);
  }
}
