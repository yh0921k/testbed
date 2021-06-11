package com.testbed.domains.jasypt.application.dto;

import lombok.Getter;

@Getter
public class ConfigEncryptRequest {
  String password;
  String configText;
}
