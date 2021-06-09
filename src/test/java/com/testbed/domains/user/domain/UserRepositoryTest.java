package com.testbed.domains.user.domain;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserRepositoryTest {

  @Autowired private UserRepository userRepository;

  @Test
  public void basicCrudOperations() {
    User user = User.builder().name("kkk").password("112233").build();

    // when
    User savedUser = userRepository.save(user);

    // then
    Optional<User> findUser = userRepository.findById(savedUser.getId());

    assertThat(findUser.isPresent(), equalTo(Boolean.TRUE));
    assertThat(findUser.get().getName(), equalTo(savedUser.getName()));
  }
}
