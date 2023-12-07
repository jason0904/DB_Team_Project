package com.caudbdesign.dbTeamProject.Item;


import com.fasterxml.jackson.annotation.JsonProperty;
import java.sql.Timestamp;
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
    private BigInteger volume = new BigInteger("0");
    private BigInteger market_cap = new BigInteger("0");
    private float PER;
    private float EPS;
    private Timestamp created_at;



//    @JsonProperty("ceo")
//    String CEO;
//    String sector;
//    String industry;
//    Float dividend_yield;
//    Integer dividend_payment_month;
}
