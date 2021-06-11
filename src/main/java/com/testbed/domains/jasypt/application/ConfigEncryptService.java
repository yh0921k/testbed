package com.testbed.domains.jasypt.application;

import com.testbed.domains.jasypt.application.dto.ConfigEncryptRequest;
import com.testbed.domains.jasypt.domain.JasyptUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConfigEncryptService {

  private final JasyptUtils jasyptUtils;

  public String encrypt(ConfigEncryptRequest configEncryptRequest) {
    return jasyptUtils.encrypt(
        configEncryptRequest.getPassword(), configEncryptRequest.getConfigText());
  }

  public String decrypt(ConfigEncryptRequest configEncryptRequest) {
    return jasyptUtils.decrypt(
        configEncryptRequest.getPassword(), configEncryptRequest.getConfigText());
  }
}
