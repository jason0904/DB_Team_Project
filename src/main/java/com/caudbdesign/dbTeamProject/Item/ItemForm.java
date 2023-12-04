package com.caudbdesign.dbTeamProject.Item;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Getter
@Setter
public class ItemForm {

  //Item에 itemid, name, market_name, price
  private int item_id;
  private String name;
  private String market_name;
  private float price;
}
