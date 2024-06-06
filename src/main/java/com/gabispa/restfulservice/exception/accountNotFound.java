package com.gabispa.restfulservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class accountNotFound extends RuntimeException{
  public accountNotFound(String message) {
    super("Account not found");
  }
}
