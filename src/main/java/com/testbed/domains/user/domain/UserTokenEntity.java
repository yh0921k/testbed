package com.testbed.domains.user.domain;

import com.testbed.commons.exception.ExceptionState;
import com.testbed.commons.exception.JwtRuntimeException;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "user_token")
@Entity
public class UserTokenEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne
  @JoinColumn(name = "user_id")
  private UserEntity userEntity;

  private String refreshToken;

  @Builder
  public UserTokenEntity(UserEntity userEntity, String refreshToken) {
    this.userEntity = userEntity;
    this.refreshToken = refreshToken;
  }

  public UserTokenEntity refreshToken(String refreshToken) {
    this.refreshToken = refreshToken;
    return this;
  }

  public void validUser(Long parsedIndex) {
    if(this.userEntity.getId() != parsedIndex) {
      throw new JwtRuntimeException(ExceptionState.FORCE_REFRESH);
    }
  }

  public void validToken(String refreshToken) {
    if (!this.refreshToken.equals(refreshToken)) {
      throw new JwtRuntimeException(ExceptionState.FORCE_REFRESH);
    }
  }
}
