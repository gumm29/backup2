package com.loja.demo.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.loja.demo.entity.User;
import com.loja.demo.repository.UsuarioRepository;

@Service
public class EmailService {

  @Autowired
  UsuarioRepository usuarioRepositorio;

  @Autowired
  private JavaMailSender emailSender;

  public void enviarEmail(User user){
    try{
      SimpleMailMessage mensagem = new SimpleMailMessage();
      mensagem.setFrom("testegustavo29@gmail.com");
      mensagem.setTo(user.getEmail());
      mensagem.setSubject("verificacao de email");
      mensagem.setText("digite o numero "+ user.getTokenEmail());
      emailSender.send(mensagem);
    }catch(MailException e){
      e.printStackTrace();
    }
  }

  public String numeroAleatorio(){
    return Integer.toString(new Random().nextInt(50));
  }
}
