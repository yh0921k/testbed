package com.testbed.commons.exception.test;

import com.testbed.commons.exception.ExceptionState;
import lombok.Getter;

@Getter
public class TestException01 extends RuntimeException implements CustomException {
  private final ExceptionState state;

  public TestException01(ExceptionState state) {
    super(state.getMessage());
    this.state = state;
  }

  public TestException01(ExceptionState state, String message) {
    super(message);
    this.state = state;
  }
}
