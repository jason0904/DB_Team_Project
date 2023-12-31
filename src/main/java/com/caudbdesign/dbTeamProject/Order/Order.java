package com.caudbdesign.dbTeamProject.Order;


import java.sql.Timestamp;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@RequiredArgsConstructor
public class Order {

  private Integer order_id;
  private Integer account_id;
  private Integer item_id;
  private String purchase_type;
  private String order_status;
  private Timestamp created_at;
  private Timestamp success_at;

}
