package com.testbed.domains.exception;

import com.testbed.commons.exception.ExceptionState;
import com.testbed.commons.exception.test.TestException01;
import com.testbed.commons.exception.test.TestException02;
import com.testbed.commons.exception.test.TestException03;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exception")
public class ExceptionController {

  @PostMapping("/test01")
  public ResponseEntity test01() {
    if(true) {
      throw new TestException01(ExceptionState.TEST01);
    }
    return ResponseEntity.ok().build();
  }

  @PostMapping("/test02")
  public ResponseEntity test02() {
    if(true) {
      throw new TestException02(ExceptionState.TEST02);
    }
    return ResponseEntity.ok().build();
  }

  @PostMapping("/test03")
  public ResponseEntity test03() {
    if(true) {
      throw new TestException03(ExceptionState.TEST03, "My Test03 Exception");
    }
    return ResponseEntity.ok().build();
  }
}
