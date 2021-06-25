package com.testbed.commons.exception.detail;

import com.testbed.commons.exception.ExceptionState;
import lombok.Getter;

@Getter
public class AuthenticationException extends RuntimeException {
  private final ExceptionState state;

  public AuthenticationException(ExceptionState state) {
    super(state.getMessage());
    this.state = state;
  }

  public AuthenticationException(ExceptionState state, String message) {
    super(message);
    this.state = state;
  }
}
