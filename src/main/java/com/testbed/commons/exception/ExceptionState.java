package com.testbed.commons.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ExceptionState {
  AUTHENTICATION_FAILED(401, HttpStatus.UNAUTHORIZED, "AUTH001", "Invalid Authentication"),
  AUTHORIZATION_FAILED(403, HttpStatus.FORBIDDEN, "AUTH002", "Invalid Authorization"),
  INVALID_TOKEN(401, HttpStatus.UNAUTHORIZED, "AUTH003", "Invalid Auth Token"),
  FORCE_REFRESH(401, HttpStatus.UNAUTHORIZED, "AUTH004", "Sign-in Again"),

  TEST01(500, HttpStatus.BAD_REQUEST, "TEST001", "TEST001"),
  TEST02(500, HttpStatus.UNAUTHORIZED, "TEST002", "TEST002"),
  TEST03(500, HttpStatus.INTERNAL_SERVER_ERROR, "TEST003", "TEST003");

  private final int value;
  private final HttpStatus httpStatus;
  private final String code;
  private final String message;

  ExceptionState(
      final int value, final HttpStatus httpStatus, final String code, final String message) {
    this.value = value;
    this.httpStatus = httpStatus;
    this.message = message;
    this.code = code;
  }
}
