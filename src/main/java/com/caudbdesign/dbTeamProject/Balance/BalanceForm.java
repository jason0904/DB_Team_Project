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
    private float return_rate;      //
    private int quantity;           // ItemPortfolio
    private float current_value;    // ItemPortfolio** 현재가 x 보유수량 (currentPrice x quantity)
    private float weight;           // // 평가 금액/현재 시장가격 총합 ( currentValue/totalValue)
//    private double total_purchase_price;    // ItemPortfolio     // 해당 주식을 구매하면서 생긴 총 구매가.
    private float purchase_price; //: 20000    //,     매입단가 == total/quantity //
    private double purchase_amount;   //: 400000  //,   매수금액 == total //
    private float current_price;            // ItemPortfolio    // 한 주 당 현재가. CurrentPrice 에서 가져오는 현재가



//          <accountData>
//          profitLoss: 120000,
//          totalValue: 2400000,         // 현재 시장가격 총합
//          deposit: 500000,             //  Balance -> total_balance
//          totalCost: 1900000          // 평균구매 가격 총합   // ItemPortfolio -> total_purchase_price
//
//
//          <stockData>
//          id: 'stock4',
//          name: '제이슨정전자',         종목명
//          profitLoss: 20000,        평가손익      // current_value - total_purchase_price
//          returnRate: 1.0,          수익률
//          quantity: 20,             보유수량
//          currentValue: 400000,     평가금액      // 현재가 x 보유수량 (currentPrice x quantity)
//          weight: 10,               비중 (가진 주식에서 차지하는 비중)  // 평가 금액/현재 시장가격 총합 ( currentValue/totalValue)
//          purchasePrice: 20000,     매입단가
//          purchaseAmount: 400000,   매수금액
//          currentPrice: 20000,      현재가   // item_id 이용해서 CurrentPrice 에서 불러오기



}
