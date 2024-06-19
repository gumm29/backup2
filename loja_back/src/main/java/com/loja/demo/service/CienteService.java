package com.loja.demo.service;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import com.loja.demo.entity.User;
import com.loja.demo.exception.NotFoundException;
import com.loja.demo.exception.UnprocessableEntityException;
import com.loja.demo.models.CadastroDto;
import com.loja.demo.models.ListaCadatrosDto;
import com.loja.demo.models.Login;
import com.loja.demo.models.TrocaSenhaDTO;
import com.loja.demo.repository.UsuarioRepository;

@Service
public class CienteService {

  @Autowired
  UsuarioRepository usuarioRepository;

  @Autowired
  EmailService servicoEmail;

  public ResponseEntity<User> cadastrar(CadastroDto usuario, UriComponentsBuilder uriBuilder){
    Optional<User> existeCliente = usuarioRepository.findByEmail(usuario.getEmail());
    if(existeCliente.isEmpty()) {
      User user = CadastroDto.converter(usuario);
      String emailToken = servicoEmail.numeroAleatorio();
      user.setTokenEmail(emailToken);
      usuarioRepository.save(user);
      // servicoEmail.enviarEmail(user);
      URI uri = uriBuilder.path("/cliente").buildAndExpand(usuarioRepository.count()+1).toUri();
      return ResponseEntity.created(uri).body(user);
    }else{
      throw new UnprocessableEntityException("Usuario ja existe");
    }
  }

  public ResponseEntity<User> enviarEmail(Login login, UriComponentsBuilder uriBuilder){
    Optional<User> existeCliente = usuarioRepository.findByEmail(login.getEmail());
    if(existeCliente.isPresent()) {
      User user = existeCliente.get();
      if (user.isPerfilAtivado() == true){ throw new UnprocessableEntityException("Conta ja validada"); } // arrumar exeption
      String emailToken = servicoEmail.numeroAleatorio();
      user.setTokenEmail(emailToken);
      usuarioRepository.save(user);
      // servicoEmail.enviarEmail(user);
      URI uri = uriBuilder.path("/cliente").buildAndExpand(usuarioRepository.count()+1).toUri();
      return ResponseEntity.created(uri).build();
    }else{
      throw new NotFoundException("Usuario não encontrado");
    }
  }

  public ResponseEntity<User> trocaSenha(TrocaSenhaDTO login, UriComponentsBuilder uriBuilder){
    Optional<User> existeCliente = usuarioRepository.findByEmail(login.getEmail());
    if(existeCliente.isPresent()) {
      User user = existeCliente.get();
      if (user.isPerfilAtivado() == true){ throw new UnprocessableEntityException("Conta ja validada"); }
      if (user.getTokenEmail().equals(login.getEmailToken())){
        user.setSenha(new BCryptPasswordEncoder().encode(login.getNovaSenha()));
        usuarioRepository.save(user);
        URI uri = uriBuilder.path("/cliente").buildAndExpand(usuarioRepository.count()+1).toUri();
        return ResponseEntity.created(uri).build();
      }else{
        throw new UnprocessableEntityException("Token invalido");
      }
    }else{
      throw new NotFoundException("Usuario não encontrado"); 
    }
  }

  public Page<ListaCadatrosDto> listaCLiente(Pageable paginacao){
    Page<User> usuarios = usuarioRepository.findAll(paginacao);
    return ListaCadatrosDto.converter(usuarios);
  }
}
