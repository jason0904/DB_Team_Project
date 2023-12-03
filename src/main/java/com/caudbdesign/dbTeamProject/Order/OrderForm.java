package com.caudbdesign.dbTeamProject.Order;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@RequiredArgsConstructor
public class OrderForm {

  //Order와 OrderType을 합친 클래스.
  private int order_id;
  private int account_id;
  private int item_id;
  private String purchase_type;
  private String order_status;
  private int quantity;
  private int left_quantity;
  private String order_type;
  private float limit_price; //지정가.

}
