package com.testbed.commons.exception;

import com.testbed.commons.exception.detail.AuthenticationException;
import com.testbed.commons.exception.detail.AuthorizationException;
import com.testbed.commons.exception.detail.JwtRuntimeException;
import com.testbed.commons.exception.test.TestException01;
import com.testbed.commons.exception.test.TestException02;
import com.testbed.commons.exception.test.TestException03;
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

  @ExceptionHandler(TestException01.class)
  protected ResponseEntity<ExceptionResponse> handleTestException01(
      TestException01 testException01) {

    log.info("handleTestException01() : ", testException01.getMessage());
    ExceptionResponse exceptionResponse = ExceptionResponse.of(testException01);

    return ResponseEntity.status(exceptionResponse.getValue()).body(exceptionResponse);
  }

  @ExceptionHandler(TestException02.class)
  protected ResponseEntity<ExceptionResponse> handleTestException01(
      TestException02 testException02) {

    log.info("handleTestException02() : ", testException02.getMessage());
    ExceptionResponse exceptionResponse = ExceptionResponse.of(testException02);

    return ResponseEntity.status(exceptionResponse.getValue()).body(exceptionResponse);
  }

  @ExceptionHandler(TestException03.class)
  protected ResponseEntity<ExceptionResponse> handleTestException01(
      TestException03 testException03) {

    log.info("handleTestException03() : ", testException03.getMessage());
    ExceptionResponse exceptionResponse = ExceptionResponse.of(testException03);

    return ResponseEntity.status(exceptionResponse.getValue()).body(exceptionResponse);
  }
}
