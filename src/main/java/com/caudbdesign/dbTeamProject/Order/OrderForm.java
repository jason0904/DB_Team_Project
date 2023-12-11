package com.caudbdesign.dbTeamProject.Order;

import java.sql.Timestamp;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Getter
@Setter
public class OrderForm {

  //Order와 OrderType을 합친 Form
  private Integer order_id;
  private Integer account_id;
  private Integer item_id;
  private String purchase_type;
  private String order_status;
  private Timestamp created_at;
  private Timestamp success_at;
  private Integer quantity;
  private String order_type;
  private float limit_price;

}
