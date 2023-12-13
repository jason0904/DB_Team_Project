package com.caudbdesign.dbTeamProject.Balance;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter
@Setter
@RequiredArgsConstructor
public class BalanceOutputForm {

    private Integer account_id;
    private float Total_Balance;  // 총 금액  KRW 기준 // 잔고
    private float KRW_Balance;
    private float USD_Balance;
    private float return_rate;
    List<BalanceForm> ItemPortfolios;
}
