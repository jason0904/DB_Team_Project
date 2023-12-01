package com.caudbdesign.dbTeamProject.Item;

import java.sql.Timestamp;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@RequiredArgsConstructor
public class Item {

  private int Item_id;
  private String symbol;
  private String name;
  private String item_type;
  private String market;
  private Timestamp created_at;


}
