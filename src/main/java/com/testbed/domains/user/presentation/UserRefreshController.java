package com.testbed.domains.user.presentation;

import com.testbed.domains.user.application.UserRefreshService;
import com.testbed.domains.user.application.dto.UserTokenInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserRefreshController {

  private final UserRefreshService userRefreshService;

  @PatchMapping("/users/refresh")
  public ResponseEntity<UserTokenInfo> refresh(@RequestBody UserTokenInfo userTokenInfo) {
    return ResponseEntity.ok(userRefreshService.refresh(userTokenInfo));
  }
}
