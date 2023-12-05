package com.caudbdesign.dbTeamProject.Order;

import java.util.ArrayList;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor

public class OrderController {

  private final OrderService orderService;

  @PostMapping("/api/order/order")
  @CrossOrigin
  public ResponseEntity<?> order(@RequestBody OrderForm orderForm) {
    //OrderFrom을 Order와 OrderType으로 나누어서 저장.
    Order order = new Order();
    OrderType orderType = new OrderType();
    order.setAccount_id(orderForm.getAccount_id());
    order.setItem_id(orderForm.getItem_id());
    order.setPurchase_type(orderForm.getPurchase_type());
    order.setOrder_status("Pending");
    orderType.setQuantity(orderForm.getQuantity());
    orderType.setLeft_quantity(orderForm.getQuantity());
    orderType.setOrder_type(orderForm.getOrder_type());
    orderType.setLimit_price(orderForm.getLimit_price());
    orderService.order(order, orderType);
    return ResponseEntity.ok().build();
  }

  @PostMapping("/api/order/amend")
  @CrossOrigin
  public ResponseEntity<?> amend(@RequestBody OrderForm orderForm) {
    //order_id와 price를 받아서 amend.
    Integer result = orderService.amend(orderForm.getOrder_id(), orderForm.getLimit_price());
    if(result != null) {
      return ResponseEntity.ok(result);
    }
    else {
      return ResponseEntity.badRequest().build();
    }
  }

  @PostMapping("/api/order/cancel")
  @CrossOrigin
  public ResponseEntity<?> cancel(@RequestBody OrderForm orderForm) {
    //order_id를 받아서 cancel.
    boolean result = orderService.cancel(orderForm.getOrder_id());
    if(result) {
      return ResponseEntity.ok().build();
    }
    else {
      return ResponseEntity.badRequest().build();
    }
  }

  @PostMapping("/api/order/pending")
  @CrossOrigin
  public ResponseEntity<?> showPendingInfo(@RequestBody OrderForm orderForm) {
    //Order_id를 받아서 Pending인 주문의 정보를 보여줌.
    ArrayList<OrderForm> result = orderService.showPendingInfo(orderForm.getAccount_id());
    return ResponseEntity.ok(result);
  }

}
