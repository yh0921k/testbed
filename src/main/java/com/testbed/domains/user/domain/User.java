package com.testbed.domains.user.domain;

import java.io.Serializable;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class User implements Serializable {
  private String name;
  private String password;

  @Builder
  public User(String name, String password) {
    this.name = name;
    this.password = password;
  }
}
