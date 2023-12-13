package com.caudbdesign.dbTeamProject.Admin.ChangePrice;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@RequiredArgsConstructor
public class PriceForm {

  private Integer item_id;
  private float opening_price;
  private float closing_price;
  private float current_price;

}
