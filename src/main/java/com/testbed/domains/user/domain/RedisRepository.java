package com.testbed.domains.user.domain;

import org.springframework.data.repository.CrudRepository;

public interface RedisRepository extends CrudRepository<User, Long> {}
