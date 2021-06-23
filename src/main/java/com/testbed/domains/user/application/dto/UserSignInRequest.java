package com.testbed.domains.user.application.dto;

import lombok.Getter;

@Getter
public class UserSignInRequest {
  private String email;
  private String password;
}
