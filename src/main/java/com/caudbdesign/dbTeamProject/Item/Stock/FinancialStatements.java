package com.caudbdesign.dbTeamProject.Item.Stock;

import java.math.BigInteger;
import java.sql.Timestamp;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@RequiredArgsConstructor
public class FinancialStatements {

  private Integer statement_id;
  private Integer item_id;
  private String report_period;
  private BigInteger revenue;
  private BigInteger net_income;
  private BigInteger total_assets;
  private BigInteger total_liabilities;
  private BigInteger equity;
  private Timestamp report_date;

}
