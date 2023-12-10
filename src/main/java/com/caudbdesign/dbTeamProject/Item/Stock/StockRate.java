package com.caudbdesign.dbTeamProject.Item.Stock;

import java.sql.Timestamp;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Setter
@Getter
public class StockRate {

  private Integer stock_info_id;
  private Integer item_id;
  private Integer analyst_id;
  private String analyst_rating;
  private float target_price;
  private Timestamp created_at;

}
