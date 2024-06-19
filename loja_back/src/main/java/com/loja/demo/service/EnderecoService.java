package com.loja.demo.service;

import java.net.URI;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import com.loja.demo.entity.Endereco;
import com.loja.demo.entity.User;
import com.loja.demo.exception.BadRequestException;
import com.loja.demo.exception.NotFoundException;
import com.loja.demo.models.EnderecoDtoRequest;
import com.loja.demo.repository.EnderecoRepository;

@Service
public class EnderecoService {

  @Autowired
  EnderecoRepository enderecoRepository;

  @Autowired
  TokenService tokenService;

  public ResponseEntity<Endereco> listaEndereco(String token){
    User user = tokenService.autenticaClienteToken(token);
    Optional<Endereco> enderecoExiste = enderecoRepository.findByClienteId(user.getId());
    if(enderecoExiste.isPresent()){
      return ResponseEntity.ok(enderecoExiste.get());
    }else{
      throw new NotFoundException("Endereço não encontrado"); 
    }
  }

  public ResponseEntity<Endereco> salvaEndereco(EnderecoDtoRequest request, UriComponentsBuilder uriBuilder, String token){
    User user = tokenService.autenticaClienteToken(token);
    Optional<Endereco> enderecoExiste = enderecoRepository.findByClienteId(user.getId());
    if(enderecoExiste.isEmpty()){
      Endereco endereco = EnderecoDtoRequest.converter(request, user.getId());
      enderecoRepository.save(endereco);
      URI uri = uriBuilder.path("/endereco/{id}").buildAndExpand(enderecoRepository.count()+1).toUri();
      return ResponseEntity.created(uri).body(endereco);
    }else{
      throw new BadRequestException("Endereço existente"); 
    }
  }

  public ResponseEntity<Endereco> updateEndereco(UUID id, EnderecoDtoRequest request, String token) {
    //if(autenticacaoServico.verificaUsuario(token).equals("1")){ return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); }

    Optional<Endereco> enderecoExiste = enderecoRepository.findById(id);
    if(enderecoExiste.isPresent()){
      Endereco endereco = request.atualizar(id, enderecoRepository);
      return ResponseEntity.ok(endereco);
    }
    return ResponseEntity.notFound().build();
  }

  public ResponseEntity<Optional<Endereco>> deletaEndereco(UUID id, String token) {
    //if(autenticacaoServico.verificaUsuario(token).equals("1")){ return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); }

    Optional<Endereco> selecionado = enderecoRepository.findById(id);
    if (selecionado.isPresent()) {
      enderecoRepository.deleteById(id);
      return ResponseEntity.noContent().build();
    }
    return ResponseEntity.notFound().build();
  }
}
