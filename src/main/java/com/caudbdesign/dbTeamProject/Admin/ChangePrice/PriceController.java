package com.caudbdesign.dbTeamProject.Admin.ChangePrice;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class PriceController {

  private final PriceService priceService;

  @PostMapping("/admin/updatePrice")
  @CrossOrigin
  public ResponseEntity<?> updatePrice(PriceForm form) {
    priceService.updatePrice(form);
    return ResponseEntity.ok().build();
  }
}
