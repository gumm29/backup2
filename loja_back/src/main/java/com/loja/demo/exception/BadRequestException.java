package com.loja.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {

  private String mensagem;
  private int code;

  public BadRequestException(String message) {
		super();
    this.code = 400;
    this.mensagem = "teste";
	}

  public int getCode(){
    return code;
  }

  public String getMensagem() {
    return mensagem;
  }
}
