package com.caudbdesign.dbTeamProject.Log;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class StockTradeLogController {

  private final StockTradeLogService stockTradeLogService;

  @PostMapping("api/stocktradelog")
  public ResponseEntity<?> getTradeLogByAccountId(int accountId, String trade_time) {
    return ResponseEntity.ok(stockTradeLogService.getTradeLogByAccountId(accountId, trade_time));
  }


}
