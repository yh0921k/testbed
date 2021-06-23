package com.testbed.domains.user.application;

import com.testbed.commons.jwt.AuthToken;
import com.testbed.commons.jwt.JwtUtils;
import com.testbed.commons.jwt.TokenType;
import com.testbed.domains.user.domain.UserEntity;
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserAuthService {

  private final JwtUtils jwtUtils;

  public AuthToken createAccessToken(UserEntity userEntity) {
    Map<String, Object> payload = new LinkedHashMap<>();
    payload.put("email", userEntity.getEmail());
    payload.put("role", userEntity.getRole().getCode());

    return jwtUtils.createAuthToken(payload, TokenType.ACCESS_TOKEN);
  }

  public AuthToken createRefreshToken(UserEntity userEntity) {
    Map<String, Object> payload = new LinkedHashMap<>();
    payload.put("userIndex", userEntity.getId());
    payload.put("role", userEntity.getRole().getCode());

    return jwtUtils.createAuthToken(payload, TokenType.REFRESH_TOKEN);
  }

}
