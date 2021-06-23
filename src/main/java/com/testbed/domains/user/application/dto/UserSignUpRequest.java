package com.testbed.domains.user.application.dto;

import lombok.Getter;

@Getter
public class UserSignUpRequest {
  private String email;
  private String password;
  private String name;
}
