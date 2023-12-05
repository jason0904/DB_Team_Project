package com.caudbdesign.dbTeamProject.Log;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class StockTradeLogController {

  private final StockTradeLogService stockTradeLogService;

  @PostMapping("api/stocktradelog")
  @CrossOrigin
  public ResponseEntity<?> getTradeLogByAccountId(@RequestBody int account_id, String trade_time) {
    return ResponseEntity.ok(stockTradeLogService.getTradeLogByAccountId(account_id, trade_time));
  }


}
