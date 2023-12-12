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
  public ResponseEntity<?> getItems(@RequestBody ItemInputForm item) {
    return ResponseEntity.ok(itemService.getItemsbySearch(item.getSearch()));
  }

  @PostMapping("/api/item/info")
  @CrossOrigin
  public ResponseEntity<?> getStockInfo(@RequestBody ItemInputForm item) {
    return ResponseEntity.ok(itemService.getItemInfoFormbyItemid(item.getItem_id(), item.getReport_period()));
  }




}
