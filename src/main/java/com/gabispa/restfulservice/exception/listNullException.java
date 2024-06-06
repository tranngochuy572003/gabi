package com.gabispa.restfulservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NO_CONTENT)
public class listNullException extends NullPointerException {

  public listNullException(String message) {
    super(message);
  }
}





