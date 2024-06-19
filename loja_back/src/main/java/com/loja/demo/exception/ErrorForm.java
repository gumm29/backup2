package com.loja.demo.exception;

public class ErrorForm {
  private String field;
  private String mensagem;

  public ErrorForm(String field, String message) {
		super();
		
		this.field = field;
    this.mensagem = message;
	}

  public String getField() {
    return field;
  }

  public String getMensagem() {
    return mensagem;
  }
}
