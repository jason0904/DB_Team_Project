package com.caudbdesign.dbTeamProject.Order;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@RequiredArgsConstructor
public class SellOrder {

  private int order_id;
  private int quantity;
  private int left_quantity;
  private String order_type;
  private float limit_price;


}
