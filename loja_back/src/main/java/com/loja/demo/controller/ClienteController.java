package com.loja.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import com.loja.demo.entity.User;
import com.loja.demo.models.CadastroDto;
import com.loja.demo.models.ListaCadatrosDto;
import com.loja.demo.models.Login;
import com.loja.demo.models.TrocaSenhaDTO;
import com.loja.demo.models.VerificaContaDTO;
import com.loja.demo.service.AutenticacaoService;
import com.loja.demo.service.CienteService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

  @Autowired
  AutenticacaoService autenticacaoservico;

  @Autowired
  CienteService cliente;

  @PostMapping("/cadastro")
  public ResponseEntity<User> novoUsuario(@RequestBody @Valid CadastroDto usuario, UriComponentsBuilder uriBuilder){
    return cliente.cadastrar(usuario, uriBuilder);
  }

  @PostMapping("/reenvioEmail")
  public ResponseEntity<User> reenvioEmail(@RequestBody @Valid Login login, UriComponentsBuilder uriBuilder) {
    return cliente.enviarEmail(login, uriBuilder);
  }

  @PostMapping("/trocaSenha")
  public ResponseEntity<User> trocaSenha(@RequestBody @Valid TrocaSenhaDTO login, UriComponentsBuilder uriBuilder) throws Exception{
    return cliente.trocaSenha(login, uriBuilder);
  }

  @PostMapping("/validaUsuario")
  public ResponseEntity<User> verificaContaUsuario(@RequestBody VerificaContaDTO usuario, UriComponentsBuilder uriBuilder) throws Exception{
    return autenticacaoservico.verificaConta(usuario);
  }

  @GetMapping("/listaClientes")
  public Page<ListaCadatrosDto> listaClientes(@PageableDefault(sort = "id", direction = Direction.DESC, page = 0, size = 10) Pageable paginacao) {
    return cliente.listaCLiente(paginacao);
  }
}
