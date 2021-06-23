package com.testbed.domains.response.application;

import com.testbed.domains.response.application.dto.TestDTO;
import com.testbed.domains.response.domain.TestType;
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ResponseTestService {

  public TestDTO post(TestDTO testDTO) {
    System.out.println("===== ===== ===== Request ===== ===== =====");
    System.out.println(testDTO);

    Map<String, String> data = new LinkedHashMap<>();
    data.put("userIndex", "1");
    data.put("name", "test");
    data.put("password", "123123");

    return TestDTO.builder().testType(TestType.NOTIFICATION).data(data).build();
  }
}
