package com.testbed.domains.jpa;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "orders")
@Entity
@ToString
public class OrderEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String totalAmount;
  private String orderNumber;

  @OneToMany(mappedBy = "orderEntity", cascade = CascadeType.PERSIST)
  private List<OrderLineEntity> orderLineEntityList;

  @Builder
  public OrderEntity(String totalAmount, String orderNumber) {
    this.totalAmount = totalAmount;
    this.orderNumber = orderNumber;
    this.orderLineEntityList = new LinkedList<>();
  }

  public OrderEntity addOrderLine(OrderLineEntity orderLineEntity) {
    this.orderLineEntityList.add(orderLineEntity);
    return this;
  }
}
