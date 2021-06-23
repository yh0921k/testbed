package com.testbed.domains.response.application.dto;

import com.testbed.domains.response.domain.TestType;
import java.util.Map;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString
public class TestDTO {
  TestType testType;
  Map<String, String> data;
}
