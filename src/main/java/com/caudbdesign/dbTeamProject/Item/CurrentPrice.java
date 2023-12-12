package com.caudbdesign.dbTeamProject.Item;


import java.sql.Timestamp;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Setter
@Getter
public class CurrentPrice {

  private Integer item_id;
  private Timestamp updated_at;
  private float current_price;

}
