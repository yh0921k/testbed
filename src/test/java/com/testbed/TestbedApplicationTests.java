package com.testbed;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.testbed.domains.user.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;

@SpringBootTest
class TestbedApplicationTests {

  @Autowired private RedisTemplate redisTemplate;

  @Test
  void contextLoads() {}

  @Test
  void redisConnectionTest() {
    final String key = "name";
    final String value = "kyh";

    final ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
    valueOperations.set(key, value);

    final String result = valueOperations.get(key);
    assertEquals(value, result);
  }

  @Test
  void redisGetKeyBirthday() {
    ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();

    String result = valueOperations.get("birthday");
    System.out.println(result);
  }

  @Test
  void redisObjectTest() {
    User user = User.builder().name("kyh").password("123123").build();

    ValueOperations<String, User> valueOperations = redisTemplate.opsForValue();
    valueOperations.set(user.getName(), user);

    User result = valueOperations.get(user.getName());
    System.out.println(result);
  }

  @Test
  void redisSetOperationTest() {
    SetOperations<String, String> setOperations = redisTemplate.opsForSet();
    String key = "setKey";
    String[] values = {"value01", "value02", "value03"};

    for (String value : values) {
      setOperations.add(key, value);
    }

    for (String value : setOperations.members(key)) {
      System.out.println(value);
    }

    assertEquals(true, checkAllInside(key, setOperations, values));
  }

  private boolean checkAllInside(
      String key, SetOperations<String, String> setOperations, String[] values) {
    boolean flag = true;
    for (String value : values) {
      // System.out.println(value);
      if (!setOperations.isMember(key, value)) {
        flag = false;
      }
    }
    return flag;
  }
}
