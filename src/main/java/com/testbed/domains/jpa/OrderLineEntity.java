package com.testbed.domains.jpa;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "orderlines")
@Entity
@ToString
public class OrderLineEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String amount;

  @ManyToOne
  @JoinColumn(name = "order_id")
  private OrderEntity orderEntity;

  @Builder
  public OrderLineEntity(String amount, OrderEntity orderEntity) {
    this.amount = amount;
    this.orderEntity = orderEntity;
    orderEntity.addOrderLine(this);
  }
}
