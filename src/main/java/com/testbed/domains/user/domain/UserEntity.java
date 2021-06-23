package com.testbed.domains.user.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "users")
@Entity
public class UserEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String email;
  private String password;
  private String name;
  private Role role;

  @Builder
  public UserEntity(String email, String password, String name) {
    this.email = email;
    this.password = password;
    this.name = name;
    this.role = Role.USER;
  }

  public void validPassword(String password) {
    if (!this.password.equals(password)) {
      throw new IllegalArgumentException("Invalid Password");
    }
  }
}
