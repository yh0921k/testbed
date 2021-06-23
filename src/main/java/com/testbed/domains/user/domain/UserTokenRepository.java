package com.testbed.domains.user.domain;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTokenRepository extends JpaRepository<UserTokenEntity, Long> {
  boolean existsByUserEntityId(Long userIndex);
  Optional<UserTokenEntity> findByUserEntityId(Long userIndex);
}
