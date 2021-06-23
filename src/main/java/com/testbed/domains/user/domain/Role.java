package com.testbed.domains.user.domain;

import java.util.Arrays;
import lombok.Getter;

@Getter
public enum Role {
  ADMIN("ROLE_ADMIN"),
  USER("ROLE_USER"),
  UNKNOWN("UNKNOWN");

  private String code;
  private String description;

  Role(String code) {
    this.code = code;
  }

  public Role of(String code) {
    return Arrays.stream(Role.values())
        .filter(r -> r.getCode().equals(code))
        .findAny()
        .orElse(UNKNOWN);
  }
}
