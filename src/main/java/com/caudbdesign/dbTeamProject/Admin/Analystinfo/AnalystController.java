package com.caudbdesign.dbTeamProject.Admin.Analystinfo;


import com.caudbdesign.dbTeamProject.Item.Stock.AnalystInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@Slf4j
@RequiredArgsConstructor
public class AnalystController {

  private final AnalystService analystService;

  @PostMapping("/admin/deleteAnalyst")
  @CrossOrigin
  public ResponseEntity<?> deleteAnalyst(@RequestBody AnalystInfo info) {
    if(!analystService.isAnalystExist(info.getAnalyst_id())) return ResponseEntity.badRequest().build();
    analystService.deleteAnalyst(info.getAnalyst_id());
    return ResponseEntity.ok().build();
  }


}
