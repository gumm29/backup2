package com.loja.demo.service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.loja.demo.entity.User;
import com.loja.demo.repository.UsuarioRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {

  @Autowired
  UsuarioRepository usuarioRepository;

  @Value("${produto.jwt.expiration}")
  private String expiration;

  @Value("${produto.jwt.secret}")
  private String secret;

  public String gerarToken(Authentication authentication){
    User logado = (User) authentication.getPrincipal();

    Date hoje = new Date();
    Date dataExpiracao = new Date(hoje.getTime() + Long.parseLong(expiration));

    return Jwts.builder()
              .setIssuer("api de produtos")
              .setSubject(logado.getId().toString())
              .setIssuedAt(hoje)
              .setExpiration(dataExpiracao)
              .setHeaderParam("perfil", logado.getPerfil())
              .signWith(SignatureAlgorithm.HS256, secret)
              .compact();
  }

  public boolean tokenValido(String token){
    try {
      Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  public UUID getIdUsuario(String token) {
    Claims body = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
    return UUID.fromString(body.getSubject());
  }

  public User autenticaClienteToken(String bearerToken){
    String token = bearerToken.substring(7, bearerToken.length());
    UUID idUsuario = this.getIdUsuario(token);
    Optional<User> usuario = usuarioRepository.findById(idUsuario);
    return usuario.get();
  }
}