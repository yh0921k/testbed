package com.testbed.commons.exception.test;

import com.testbed.commons.exception.ExceptionState;
import lombok.Getter;

@Getter
public class TestException03 extends RuntimeException implements CustomException {
  private final ExceptionState state;

  public TestException03(ExceptionState state) {
    super(state.getMessage());
    this.state = state;
  }

  public TestException03(ExceptionState state, String message) {
    super(message);
    this.state = state;
  }
}
