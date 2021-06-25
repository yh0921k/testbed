package com.testbed.domains.user.application;

import com.testbed.commons.exception.ExceptionState;
import com.testbed.commons.exception.detail.JwtRuntimeException;
import com.testbed.commons.jwt.AuthToken;
import com.testbed.commons.jwt.JwtUtils;
import com.testbed.domains.user.application.dto.UserTokenInfo;
import com.testbed.domains.user.domain.UserRepository;
import com.testbed.domains.user.domain.UserTokenEntity;
import com.testbed.domains.user.domain.UserTokenRepository;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserRefreshService {

  private final UserTokenRepository userTokenRepository;
  private final UserRepository userRepository;
  private final JwtUtils jwtUtils;
  private final UserAuthService userAuthService;

  @Transactional
  public UserTokenInfo refresh(UserTokenInfo userTokenInfo) {
    AuthToken refreshToken = jwtUtils.convertAuthToken(userTokenInfo.getRefreshToken());
    UserTokenEntity userTokenEntity =
        userTokenRepository
            .findByUserEntityId(userTokenInfo.getUserIndex())
            .orElseThrow(() -> new IllegalArgumentException("Invalid User"));

    Long parsedIndex = -1L;
    try {
      parsedIndex = Long.parseLong(String.valueOf(refreshToken.getData().get("userIndex")));
    } catch (Exception exception) {
      throw new JwtRuntimeException(ExceptionState.FORCE_REFRESH);
    }
    userTokenEntity.validUser(parsedIndex);
    userTokenEntity.validToken(refreshToken.getValue());

    AuthToken accessToken = userAuthService.createAccessToken(userTokenEntity.getUserEntity());
    return UserTokenInfo.builder()
        .userIndex(parsedIndex)
        .accessToken(accessToken.getValue())
        .refreshToken(refreshToken.getValue())
        .build();
  }
}
