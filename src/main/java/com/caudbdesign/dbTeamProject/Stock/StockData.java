package com.caudbdesign.dbTeamProject.Stock;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.math.BigInteger;

@Component
@Getter
@Setter
@RequiredArgsConstructor
public class StockData {

    private int item_id;
    BigInteger volume = new BigInteger("0");
    BigInteger market_cap = new BigInteger("0");


//    @JsonProperty("ceo")
//    String CEO;
//    String sector;
//    String industry;
//    Float dividend_yield;
//    Integer dividend_payment_month;
}
