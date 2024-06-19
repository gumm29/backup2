package com.loja.demo.exception;

public class Error {
  private int code;
  private String mensagem;

  public Error(String message, int code) {
		super();
		
		this.code = code;
    this.mensagem = message;
	}

  public int getCode() {
    return code;
  }

  public String getMensagem() {
    return mensagem;
  }
}
