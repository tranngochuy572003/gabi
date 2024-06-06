package com.gabispa.restfulservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)

public class idNotFound extends RuntimeException{
  public idNotFound(String message) {
    super(message);

  }
}
