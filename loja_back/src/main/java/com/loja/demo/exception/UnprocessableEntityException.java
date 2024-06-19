package com.loja.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class UnprocessableEntityException extends RuntimeException {

  private String mensagem;

  public UnprocessableEntityException(String message) {
		super();
    this.mensagem = message;
	}

  public String getMensagem() {
    return mensagem;
  }
}
