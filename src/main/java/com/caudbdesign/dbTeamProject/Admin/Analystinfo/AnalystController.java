package com.caudbdesign.dbTeamProject.Admin.Analystinfo;


import com.caudbdesign.dbTeamProject.Item.Stock.AnalystInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class AnalystController {

  private final AnalystService analystService;

  @PostMapping("/admin/deleteAnalyst")
  @CrossOrigin
  public void deleteAnalyst(@RequestBody AnalystInfo info) {
    analystService.deleteAnalyst(info.getAnalyst_id());
  }


}
