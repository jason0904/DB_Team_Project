package com.caudbdesign.dbTeamProject.Order;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Getter
@Setter
public class OrderLeftForm {
    private int quantity;
    private float limit_price;
}
