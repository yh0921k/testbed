package com.testbed.commons.exception;

import com.testbed.commons.exception.detail.AuthenticationException;
import com.testbed.commons.exception.detail.AuthorizationException;
import com.testbed.commons.exception.detail.JwtRuntimeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(AuthenticationException.class)
  protected ResponseEntity<ExceptionResponse> handleAuthenticationException(
      AuthenticationException authenticationException) {

    log.info("handleAuthenticationException() : ", authenticationException.getMessage());

    ExceptionState exceptionState = authenticationException.getState();
    HttpStatus httpStatus = exceptionState.getHttpStatus();
    ExceptionResponse exceptionResponse =
        ExceptionResponse.builder()
            .value(exceptionState.getValue())
            .reason(httpStatus.getReasonPhrase())
            .code(exceptionState.getCode())
            .message(authenticationException.getMessage())
            .build();

    return ResponseEntity.status(httpStatus).body(exceptionResponse);
  }

  @ExceptionHandler(AuthorizationException.class)
  protected ResponseEntity<ExceptionResponse> handleAuthorizationException(
      AuthorizationException authorizationException) {

    log.info("handleAuthorizationException() : ", authorizationException.getMessage());

    ExceptionState exceptionState = authorizationException.getState();
    HttpStatus httpStatus = exceptionState.getHttpStatus();
    ExceptionResponse exceptionResponse =
        ExceptionResponse.builder()
            .value(exceptionState.getValue())
            .reason(httpStatus.getReasonPhrase())
            .code(exceptionState.getCode())
            .message(authorizationException.getMessage())
            .build();

    return ResponseEntity.status(httpStatus).body(exceptionResponse);
  }

  @ExceptionHandler(JwtRuntimeException.class)
  protected ResponseEntity<ExceptionResponse> handleJwtRuntimeException(
      JwtRuntimeException jwtRuntimeException) {

    log.info("handleJwtRuntimeException() : ", jwtRuntimeException.getMessage());

    ExceptionState exceptionState = jwtRuntimeException.getState();
    HttpStatus httpStatus = exceptionState.getHttpStatus();
    ExceptionResponse exceptionResponse =
        ExceptionResponse.builder()
            .value(exceptionState.getValue())
            .reason(httpStatus.getReasonPhrase())
            .code(exceptionState.getCode())
            .message(jwtRuntimeException.getMessage())
            .build();

    return ResponseEntity.status(httpStatus).body(exceptionResponse);
  }
}
