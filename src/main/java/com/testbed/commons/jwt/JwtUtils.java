package com.testbed.commons.jwt;

import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Base64;
import java.util.Date;
import java.util.Map;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class JwtUtils {

  @Value("${jwt.secret}")
  private String secret;

  private Key key;

  @PostConstruct
  public void init() {
    this.key = Keys.hmacShaKeyFor(Base64.getEncoder().encode(secret.getBytes()));
  }

  public AuthToken createAuthToken(Map<String, Object> payload, TokenType tokenType) {
    addCommonPayload(payload, tokenType);
    return new AuthToken(payload, key);
  }

  public AuthToken convertAuthToken(String token) {
    return new AuthToken(token, key);
  }

  public Date createExpiredDate(long minutes) {
    return Date.from(
        LocalDateTime.now().plusMinutes(minutes).atZone(ZoneId.systemDefault()).toInstant());
  }

  public void addCommonPayload(Map<String, Object> payload, TokenType tokenType) {
    Date expiredDate = createExpiredDate(TokenType.getExpiredMinutes(tokenType.getName()));
    payload.put(
        "sub",
        tokenType == TokenType.ACCESS_TOKEN ? payload.get("email") : payload.get("userIndex"));
    payload.put("iat", new Date());
    payload.put("exp", expiredDate);
  }
}
