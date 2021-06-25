package com.testbed.commons.exception;

import com.testbed.commons.exception.test.CustomException;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@Builder
public class ExceptionResponse {
  private int value;
  private String reason;
  private String code;
  private String message;

  public static ExceptionResponse of(CustomException customException) {
    ExceptionState state = customException.getState();
    HttpStatus httpStatus = state.getHttpStatus();

    return ExceptionResponse.builder()
        .value(state.getValue())
        .reason(httpStatus.getReasonPhrase())
        .code(state.getCode())
        .message(customException.getMessage())
        .build();
  }
}
