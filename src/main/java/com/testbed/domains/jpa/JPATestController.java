package com.testbed.domains.jpa;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class JPATestController {

  private final OrderRepository orderRepository;
  private final OrderLineRepository orderLineRepository;

  @PostMapping("/jpa/order")
  public ResponseEntity save() {
    OrderEntity orderEntity =
        OrderEntity.builder().orderNumber("TestNumber").totalAmount("99").build();
    OrderLineEntity orderLineEntity =
        OrderLineEntity.builder().amount("99").orderEntity(orderEntity).build();

    orderRepository.save(orderEntity);
    orderLineRepository.save(orderLineEntity);
    return ResponseEntity.ok().build();
  }

  @GetMapping("/jpa/order")
  public ResponseEntity find() {
    OrderEntity orderEntity = orderRepository.findById(1L).get();
    List<OrderLineEntity> orderLineEntityList = orderEntity.getOrderLineEntityList();

    System.out.println(orderLineEntityList.size());
    System.out.println(orderLineEntityList.stream().findFirst().get().getAmount());

    return ResponseEntity.ok().build();
  }
}
