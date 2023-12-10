package com.caudbdesign.dbTeamProject.Order;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class OrderController {

  private final OrderService orderService;

  @PostMapping("/order/order")
  public ResponseEntity<?> order(OrderForm orderForm) {
    Order order = new Order();
    OrderType orderType = new OrderType();
    order.setAccount_id(orderForm.getAccount_id());
    order.setItem_id(orderForm.getItem_id());
    order.setPurchase_type(orderForm.getPurchase_type());
    order.setOrder_status("pending");
    orderType.setQuantity(orderForm.getQuantity());
    orderType.setOrder_type(orderForm.getOrder_type());
    orderType.setLimit_price(orderForm.getLimit_price());
    if(orderService.createOrder(order, orderType)) return ResponseEntity.ok().build();
    return ResponseEntity.badRequest().build();
  }

  @PostMapping("/order/delete")
  public ResponseEntity<?> deleteOrder(OrderForm orderForm) {
    if(orderService.deleteOrder(orderForm.getOrder_id())) return ResponseEntity.ok().build();
    return ResponseEntity.badRequest().build();
  }

  @PostMapping("/order/amend")
  public ResponseEntity<?> amendOrder(OrderForm orderForm) {
    if(orderService.amendOrder(orderForm.getOrder_id(), orderForm.getQuantity())) return ResponseEntity.ok().build();
    return ResponseEntity.badRequest().build();
  }

}
