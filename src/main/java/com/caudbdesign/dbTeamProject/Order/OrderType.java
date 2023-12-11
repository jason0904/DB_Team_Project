package com.caudbdesign.dbTeamProject.Order;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@RequiredArgsConstructor

public class OrderType {

  private Integer order_id;
  private Integer quantity;
  private String order_type;
  private float limit_price;

}
