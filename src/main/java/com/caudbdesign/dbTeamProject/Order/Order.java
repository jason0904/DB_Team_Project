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
public class Order {

  private int order_id;
  private int account_id;
  private int item_id;
  private String purchase_type;
  private String order_status;
  private Timestamp created_at;

}
