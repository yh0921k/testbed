package com.testbed.domains.user.presentation;

import com.testbed.domains.user.application.UserFindService;
import com.testbed.domains.user.application.dto.UserFindResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserFindController {
  private final UserFindService userFindService;

  @GetMapping("/users/{userIndex}")
  public ResponseEntity<UserFindResponse> findByIndex(@PathVariable Long userIndex) {
    return ResponseEntity.ok(userFindService.findByIndex(userIndex));
  }
}
