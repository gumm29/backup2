package com.loja.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {

  private String mensagem;
  private int code;

  public NotFoundException(String message) {
		super();
    this.code = 404;
    this.mensagem = message;
	}

  public int getCode(){
    return code;
  }

  public String getMensagem() {
    return mensagem;
  }
}
