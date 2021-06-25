package com.testbed.commons.exception.detail;

import com.testbed.commons.exception.ExceptionState;
import lombok.Getter;

@Getter
public class JwtRuntimeException extends RuntimeException {
  private final ExceptionState state;

  public JwtRuntimeException(ExceptionState state) {
    super(state.getMessage());
    this.state = state;
  }

  public JwtRuntimeException(ExceptionState state, String message) {
    super(message);
    this.state = state;
  }
}
