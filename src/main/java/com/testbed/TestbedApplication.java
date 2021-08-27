package com.testbed;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class TestbedApplication {
  public static void main(String[] args) {
    SpringApplication.run(TestbedApplication.class, args);
  }
}
