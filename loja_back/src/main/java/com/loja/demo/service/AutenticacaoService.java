package com.loja.demo.service;

import java.util.Optional;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.loja.demo.controller.AutenticacaoController;
import com.loja.demo.entity.User;
import com.loja.demo.models.Login;
import com.loja.demo.models.Token;
import com.loja.demo.models.VerificaContaDTO;
import com.loja.demo.repository.UsuarioRepository;

@Service
public class AutenticacaoService implements UserDetailsService {

  private static final Logger logger = LoggerFactory.getLogger(AutenticacaoController.class);

  @Autowired
  private UsuarioRepository repository;

  @Autowired
  private TokenService tokenService;

  @Autowired
  private UsuarioRepository usuarioRepository;

  @Override
  public UserDetails loadUserByUsername(String userName){
    Optional<User> usuario = repository.findByEmail(userName);
    if (usuario.isPresent()) return usuario.get();
    throw new UsernameNotFoundException("Usuario inexistente");
  }

  public String verificaUsuario(String token){
    String novoToken = token.substring(7, token.length());
    String[] split_string = novoToken.split("\\.");
    String base64EncodedHeader = split_string[0];
    java.util.Base64.Decoder base64 = java.util.Base64.getUrlDecoder();
    String header = new String(base64.decode(base64EncodedHeader));
    JSONObject objPerfil = new JSONObject(header);
    return objPerfil.get("perfil").toString();
  }

  public ResponseEntity<User> verificaConta(VerificaContaDTO conta) throws Exception{
    Optional<User> usuario = repository.findByEmail(conta.getEmail());
    if(usuario.isPresent()){
      User user = usuario.get();
      if(user.getTokenEmail().equals(conta.getTokenEmail())){
        user.setPerfilAtivado(true);
        repository.save(user);
        return ResponseEntity.ok().build();
      }else{
        throw new Exception("Token inválido");
      }
    }else{
      throw new Exception("Usuario nao encontrado");
    }
  }

  public ResponseEntity<Token> autentica(Login login, AuthenticationManager authManager){
    UsernamePasswordAuthenticationToken dadosLogin = login.covert();
    try {
      Authentication authentication = authManager.authenticate(dadosLogin);
      String token = tokenService.gerarToken(authentication);
      logger.info(token);
      return ResponseEntity.ok(new Token(token, "Bearer"));
    } catch (AuthenticationException e) {
      return ResponseEntity.badRequest().build();
    }
  }

  public ResponseEntity<Token> autenticaCLiente(Login login, AuthenticationManager authManager){
    Optional<User> usuario = usuarioRepository.findByEmail(login.getEmail());
    User user = usuario.get();
    if(user.getPerfil() == 1 && user.isEnabled() == true){
      UsernamePasswordAuthenticationToken dadosLogin = login.covert();
      try {
        Authentication authentication = authManager.authenticate(dadosLogin);
        String token = tokenService.gerarToken(authentication);
        logger.info(token);
        return ResponseEntity.ok(new Token(token, "Bearer"));
      } catch (AuthenticationException e) {
        return ResponseEntity.badRequest().build();
      }
    }else{
      return ResponseEntity.badRequest().build();
    }
  }
}
