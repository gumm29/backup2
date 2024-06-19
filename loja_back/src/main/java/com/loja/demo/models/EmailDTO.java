package com.loja.demo.models;

public class EmailDTO {
  private String emailFrom;
  private String emailTo;
  private String assunto;
  private String mensagem;

  public EmailDTO(){}

  public String getEmailFrom() { return emailFrom; }
  public void setEmailFrom(String emailFrom) { this.emailFrom = emailFrom; }

  public String getEmailTo() { return emailTo; }
  public void setEmailTo(String emailTo) { this.emailTo = emailTo; }

  public String getAssunto() { return assunto; }
  public void setAssunto(String assunto) { this.assunto = assunto; }

  public String getMensagem() { return mensagem; }
  public void setMensagem(String mensagem) { this.mensagem = mensagem; }
}
