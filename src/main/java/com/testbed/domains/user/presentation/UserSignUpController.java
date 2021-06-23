package com.testbed.domains.user.presentation;

import com.testbed.domains.user.application.UserSignUpService;
import com.testbed.domains.user.application.dto.UserSignInRequest;
import com.testbed.domains.user.application.dto.UserSignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserSignUpController {

  private final UserSignUpService userSignUpService;

  @PostMapping("/sign-up")
  public ResponseEntity singUp(@RequestBody UserSignUpRequest userSignUpRequest) {
    userSignUpService.signUp(userSignUpRequest);
    return ResponseEntity.ok().build();
  }
}
