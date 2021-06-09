package com.testbed.domains.user.domain;

import java.io.Serializable;
import javax.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.redis.core.RedisHash;

@Getter
@ToString
@RedisHash("users")
public class User implements Serializable {
  @Id
  private Long id;
  private String name;
  private String password;

  @Builder
  public User(String name, String password) {
    this.name = name;
    this.password = password;
  }
}
