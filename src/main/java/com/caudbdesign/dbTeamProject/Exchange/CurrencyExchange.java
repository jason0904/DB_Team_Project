package com.caudbdesign.dbTeamProject.Exchange;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Setter
@Getter
@RequiredArgsConstructor
public class CurrencyExchange {

  private Integer exchange_id;
  private Integer account_id;
  private String base_currency;
  private String foreign_currency;
  private float base_amount;
  private float foreign_amount;
  private float exchange_time;

}
