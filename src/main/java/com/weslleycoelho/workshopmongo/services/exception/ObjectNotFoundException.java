package com.weslleycoelho.workshopmongo.services.exception;

public class ObjectNotFoundException extends RuntimeException {
  public ObjectNotFoundException(String message) {
    super(message);
  }
}
