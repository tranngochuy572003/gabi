package com.gabispa.restfulservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class invalidFieldException extends RuntimeException {
  public invalidFieldException(String message) {
    super(message);
  }
}
