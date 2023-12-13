package com.caudbdesign.dbTeamProject.Balance;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
@RequiredArgsConstructor
@Getter
@Setter
public class BalanceForm {

//    accountData + stockData
//    private Integer account_id;
//    private float total_balance;  // 총 금액  KRW 기준 // 잔고
//    private float krw_balance;
//    private float usd_balance;

//    <stockData>
    private Integer item_id;        // ItemPortfolio
    private String name;            // 종목명
    private float profit_loss;      // 평가손익
    private int quantity;           // ItemPortfolio
    private float current_value;    // ItemPortfolio** 현재가 x 보유수량 (currentPrice x quantity)
    private float weight;           // // 평가 금액/현재 시장가격 총합 ( currentValue/totalValue)
//    private double total_purchase_price;    // ItemPortfolio     // 해당 주식을 구매하면서 생긴 총 구매가.
    private float purchase_price; //: 20000    //,     매입단가 == total/quantity //
    private double purchase_amount;   //: 400000  //,   매수금액 == total //
    private float current_price;            // ItemPortfolio    // 한 주 당 현재가. CurrentPrice 에서 가져오는 현재가




}
