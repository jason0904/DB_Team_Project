package com.caudbdesign.dbTeamProject.Item;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@RequiredArgsConstructor
public class ItemInputForm {

  private Integer item_id;
  private String report_period;
  private String search;
}
