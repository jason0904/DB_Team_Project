package com.caudbdesign.dbTeamProject.Order;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Getter
@Setter
public class OrderPageForm {

//    주문창 페이지 created - account_id, itemid
//    / balance, 주식 종목 명, current_price, start_price, 현재 나와있는 물량(가격,수량)
    private float balance;
    private String name;
    private float current_price;
    private float start_price;
    List<OrderLeftForm> order_left_form;
}
