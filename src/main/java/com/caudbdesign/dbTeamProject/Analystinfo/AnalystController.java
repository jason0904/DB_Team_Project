package com.caudbdesign.dbTeamProject.Analystinfo;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class AnalystController {

  private final AnalystService analystService;

  public void deleteAnalyst(int analyst_id) {
    analystService.deleteAnalyst(analyst_id);
  }


}
