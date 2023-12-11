package com.caudbdesign.dbTeamProject.Item.Stock;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Getter
@Setter
public class AnalystInfo {

  private Integer analyst_id;
  private String name;
  private String firm;
  private String expertise_area;
  private String contact_info;

}
