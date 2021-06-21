package com.testbed.domains.response.presentation;

import com.testbed.domains.response.application.ResponseTestService;
import com.testbed.domains.response.application.dto.TestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ResponseTestController {

  private final ResponseTestService responseTestService;

  @PostMapping("/response")
  public ResponseEntity<TestDTO> post(@RequestBody TestDTO testDTO) {
    return ResponseEntity.ok(responseTestService.post(testDTO));
  }
}
