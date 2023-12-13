package com.caudbdesign.dbTeamProject.Admin.ChangePrice;

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
public class PriceController {

  private final PriceService priceService;

  @PostMapping("/admin/updatePrice")
  @CrossOrigin
  public ResponseEntity<?> updatePrice(@RequestBody PriceForm form) {
    priceService.updatePrice(form);
    return ResponseEntity.ok().build();
  }
}
