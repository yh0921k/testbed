package com.testbed.commons.exception.detail;

import com.testbed.commons.exception.ExceptionState;
import lombok.Getter;

@Getter
public class AuthorizationException extends RuntimeException {
  private final ExceptionState state;

  public AuthorizationException(ExceptionState state) {
    super(state.getMessage());
    this.state = state;
  }

  public AuthorizationException(ExceptionState state, String message) {
    super(message);
    this.state = state;
  }
}
