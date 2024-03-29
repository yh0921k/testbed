package com.testbed.commons.jwt;

import com.testbed.commons.exception.detail.AuthenticationException;
import com.testbed.commons.exception.detail.AuthorizationException;
import com.testbed.commons.exception.ExceptionState;
import com.testbed.domains.user.domain.Role;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthInterceptor implements HandlerInterceptor {

  private final JwtUtils jwtUtils;

  @Override
  public boolean preHandle(
      HttpServletRequest servletRequest, HttpServletResponse servletResponse, Object handler)
      throws Exception {

    Optional<String> token = parseToken(servletRequest);
    if (token.isEmpty()) {
      throw new AuthenticationException(ExceptionState.AUTHENTICATION_FAILED, "Token not exists");
    }

    AuthToken authToken = jwtUtils.convertAuthToken(token.get());
    authToken.validate();
    if (Role.USER.getCode().equals(authToken.getData().get("role"))) {
      return true;
    } else {
      throw new AuthorizationException(ExceptionState.AUTHORIZATION_FAILED, "Invalid Role");
    }
  }

  private Optional<String> parseToken(HttpServletRequest request) {
    String authToken = request.getHeader(HttpHeaders.AUTHORIZATION);
    if (StringUtils.hasText(authToken)) {
      return Optional.of(authToken);
    } else {
      return Optional.empty();
    }
  }
}
