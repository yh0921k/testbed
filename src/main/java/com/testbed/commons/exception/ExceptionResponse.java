package com.testbed.commons.exception;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ExceptionResponse {
  private int value;
  private String reason;
  private String code;
  private String message;
}
