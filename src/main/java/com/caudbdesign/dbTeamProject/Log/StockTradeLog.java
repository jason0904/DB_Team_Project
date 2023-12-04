package com.caudbdesign.dbTeamProject.Log;

import java.sql.Timestamp;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Getter
@Setter
public class StockTradeLog {

  private int trade_id;
  private int order_id;
  private String purchase_type;
  private int executed_quantity;
  private float executed_price;
  private Timestamp trade_time;

}
