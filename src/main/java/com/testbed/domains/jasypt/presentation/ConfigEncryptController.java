package com.testbed.domains.jasypt.presentation;

import com.testbed.domains.jasypt.application.ConfigEncryptService;
import com.testbed.domains.jasypt.application.dto.ConfigEncryptRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ConfigEncryptController {

  private final ConfigEncryptService configEncryptService;

  @PostMapping("/encrypt")
  public ResponseEntity<String> encrypt(@RequestBody ConfigEncryptRequest configEncryptRequest) {
    String cipherText = configEncryptService.encrypt(configEncryptRequest);
    return ResponseEntity.ok(cipherText);
  }

  @PostMapping("/decrypt")
  public ResponseEntity<String> decrypt(@RequestBody ConfigEncryptRequest configEncryptRequest) {
    String plainText = configEncryptService.decrypt(configEncryptRequest);
    return ResponseEntity.ok(plainText);
  }
}
