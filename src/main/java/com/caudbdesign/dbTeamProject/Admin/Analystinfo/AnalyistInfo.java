package com.caudbdesign.dbTeamProject.Admin.Analystinfo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@RequiredArgsConstructor
public class AnalyistInfo {

  private Integer analyst_id;
  private String name;
  private String firm;
  private String expertise_area;
  private String contact_info;
}
