package com.caudbdesign.dbTeamProject.Item;

import java.sql.Time;
import java.sql.Timestamp;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@RequiredArgsConstructor
public class ItemPrice_Info {

  private int item_id;
  private String currency;
  private float current_price;
  private float closing_price;
  private float opening_price;
  private float daily_high;
  private float daily_low;
  private float week_52_high;
  private float week_52_low;
  private float historical_high;
  private float historical_low;
  private Timestamp updated_at;

}
