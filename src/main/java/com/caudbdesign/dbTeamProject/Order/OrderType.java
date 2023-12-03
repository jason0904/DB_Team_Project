package com.caudbdesign.dbTeamProject.Order;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Setter
@Getter

public class OrderType {

  private int order_id;
  private int quantity;
  private int left_quantity;
  private String order_type;
  private float limit_price; //지정가.

}
