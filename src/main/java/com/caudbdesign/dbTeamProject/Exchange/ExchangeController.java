package com.caudbdesign.dbTeamProject.Exchange;

import com.caudbdesign.dbTeamProject.Balance.Balance;
import com.caudbdesign.dbTeamProject.Balance.BalanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
@RequiredArgsConstructor
public class ExchangeController {

  private final ExchangeService exchangeService;
  private final BalanceService balanceService;

  //환전 - account_id / 원화, 달러 잔고, 현재 환율(KRW -> USD)
  @PostMapping("/api/exchange")
  @CrossOrigin
  public ResponseEntity<?> showExchange(@RequestBody CurrencyExchange currencyExchange) {
    Integer account_id = currencyExchange.getAccount_id();
    ExchangeForm exchangeForm = exchangeService.showExchangeForm(account_id);
    return ResponseEntity.ok(exchangeForm);
  }

  // 환전 신청 - account_id, from통화, to 통화, to 통화기준 금액/ 성공여부, 환전후 원화잔고, 달러잔고
  @PostMapping("/api/exchange/exchange")
  @CrossOrigin
  public ResponseEntity<?> exchange(@RequestBody CurrencyExchange currencyExchange) {
    Integer account_id = currencyExchange.getAccount_id();
    float base_amount = currencyExchange.getBase_amount();
    String base_currency = currencyExchange.getBase_currency();
    String foreign_currency = currencyExchange.getForeign_currency();
    boolean result = exchangeService.Exchange(account_id, base_amount, base_currency, foreign_currency);
    if(result) {
      Balance balance = balanceService.selectBalance(account_id);
      return ResponseEntity.ok(balance);
    }
    else {
      return ResponseEntity.badRequest().build();
    }
  }

  //무조건 있는 걸로 가정.
  @PostMapping("/api/exchange/rate")
  @CrossOrigin
  public ResponseEntity<?> gerRate(@RequestBody ExchangeRateHistory exchangeRateHistory) {
    return ResponseEntity.ok(exchangeService.showExchangeRateByDay(exchangeRateHistory.getBase_currency(), exchangeRateHistory.getForeign_currency(),
        String.valueOf(exchangeRateHistory.getCreated_at())));
  }
}
