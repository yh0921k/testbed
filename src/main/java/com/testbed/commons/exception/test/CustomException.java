package com.testbed.commons.exception.test;

import com.testbed.commons.exception.ExceptionState;

public interface CustomException {
  ExceptionState getState();

  String getMessage();
}
