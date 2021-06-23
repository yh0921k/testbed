package com.testbed.domains.user.application;

import com.testbed.commons.jwt.AuthToken;
import com.testbed.domains.user.application.dto.UserSignInRequest;
import com.testbed.domains.user.application.dto.UserTokenInfo;
import com.testbed.domains.user.domain.UserEntity;
import com.testbed.domains.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserSignInService {
  private final UserRepository userRepository;
  private final UserAuthService userAuthService;

  public UserTokenInfo signIn(UserSignInRequest userSignInRequest) {
    UserEntity userEntity =
        userRepository
            .findByEmail(userSignInRequest.getEmail())
            .orElseThrow(() -> new IllegalArgumentException("Invalid Email"));

    userEntity.validPassword(userSignInRequest.getPassword());

    AuthToken accessToken = userAuthService.createAccessToken(userEntity);
    AuthToken refreshToken = userAuthService.createRefreshToken(userEntity);

    return UserTokenInfo.builder()
        .userIndex(userEntity.getId())
        .accessToken(accessToken.getValue())
        .refreshToken(refreshToken.getValue())
        .build();
  }
}
