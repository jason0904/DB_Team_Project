package com.caudbdesign.dbTeamProject.Exchange;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Getter
@Setter
public class ExchangeForm {

  //KRW,USD Balance
  private float KRW_Balance;
  private float USD_Balance;
  //Current Exchange Rate -> 원화 -> 달러
  private float current_exchange_rate;

}
