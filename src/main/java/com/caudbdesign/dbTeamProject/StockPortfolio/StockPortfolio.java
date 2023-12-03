package com.caudbdesign.dbTeamProject.StockPortfolio;

import java.sql.Timestamp;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@RequiredArgsConstructor
public class StockPortfolio {

  private Integer portfolio_id;
  private Integer account_id;
  private String stock_symbol;
  private int quantity;
  private float average_purchase_price;
  private float current_price;
  private Timestamp created_at;

}