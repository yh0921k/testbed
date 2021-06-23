package com.testbed.domains.user.application;

import com.testbed.domains.user.application.dto.UserSignUpRequest;
import com.testbed.domains.user.domain.UserEntity;
import com.testbed.domains.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserSignUpService {

  private final UserRepository userRepository;

  public void signUp(UserSignUpRequest userSignUpRequest) {
    UserEntity userEntity =
        UserEntity.builder()
            .email(userSignUpRequest.getEmail())
            .password(userSignUpRequest.getPassword())
            .name(userSignUpRequest.getName())
            .build();
    userRepository.save(userEntity);
  }
}
