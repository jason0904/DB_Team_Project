package com.caudbdesign.dbTeamProject.Item;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@RequiredArgsConstructor
public class Stock {

    private int item_id;
    @JsonProperty("ceo")
    String CEO;
    String sector;
    String industry;
    Float dividend_yield;
    Integer dividend_payment_month;
}
