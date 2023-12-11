package com.caudbdesign.dbTeamProject.Exchange;


import java.sql.Timestamp;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@RequiredArgsConstructor
public class ExchangeRateHistory {

  private String base_currency;
  private String foreign_currency;
  private float exchange_rate;
  private Timestamp created_at; // 기록
}
