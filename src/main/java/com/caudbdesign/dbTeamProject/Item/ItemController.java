package com.caudbdesign.dbTeamProject.Item;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class ItemController {

  private final ItemService itemService;

  @PostMapping("/api/item")
  @CrossOrigin
  public ResponseEntity<?> getItems(@RequestBody String search) {
    return ResponseEntity.ok(itemService.getItemsbySearch(search));
  }

  @PostMapping("/api/item/")
  @CrossOrigin
  public ResponseEntity<?> getStockInfo(@RequestBody int itemId) {

  }


}
