package com.testbed.domains.user.application;

import com.testbed.domains.user.application.dto.UserFindResponse;
import com.testbed.domains.user.domain.UserEntity;
import com.testbed.domains.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserFindService {
  private final UserRepository userRepository;

  public UserFindResponse findByIndex(Long userIndex) {
    UserEntity userEntity =
        userRepository
            .findById(userIndex)
            .orElseThrow(() -> new IllegalArgumentException("Invalid User Index"));

    return UserFindResponse.builder()
        .email(userEntity.getEmail())
        .name(userEntity.getName())
        .build();
  }
}
