package com.testbed.domains.user.presentation;

import com.testbed.domains.user.application.UserSignInService;
import com.testbed.domains.user.application.dto.UserSignInRequest;
import com.testbed.domains.user.application.dto.UserSignInResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserSignInController {

  private final UserSignInService userSignInService;

  @PostMapping("/sign-in")
  public ResponseEntity<UserSignInResponse> signIn(
      @RequestBody UserSignInRequest userSignInRequest) {
    return ResponseEntity.ok(userSignInService.signIn(userSignInRequest));
  }
}
