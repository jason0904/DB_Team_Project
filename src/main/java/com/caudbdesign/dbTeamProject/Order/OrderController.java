package com.caudbdesign.dbTeamProject.Order;

import com.caudbdesign.dbTeamProject.Item.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class OrderController {

  private final OrderService orderService;
  private final ItemService itemService;
  private final OrderSuccess orderSuccess;

  @PostMapping("api/order/order")
  public ResponseEntity<?> order(@RequestBody OrderForm orderForm) {
    Order order = new Order();
    OrderType orderType = new OrderType();
    order.setAccount_id(orderForm.getAccount_id());
    order.setItem_id(orderForm.getItem_id());
    order.setPurchase_type(orderForm.getPurchase_type());
    order.setOrder_status("pending");
    orderType.setQuantity(orderForm.getQuantity());
    orderType.setOrder_type(orderForm.getOrder_type());
    if(orderType.getOrder_type().equals("limit")) orderType.setLimit_price(orderForm.getLimit_price());
    else orderType.setLimit_price(itemService.getCurrentPriceByItemId(orderForm.getItem_id()));
    if(orderService.createOrder(order, orderType)) {
      String market = itemService.getmarketByItemId(order.getItem_id());
      orderSuccess.updateOrderStatus(order.getOrder_id(), orderType.getQuantity(), order.getItem_id(), order.getAccount_id(), order.getPurchase_type(), market, orderType.getLimit_price());
      return ResponseEntity.ok().build();
    }
    return ResponseEntity.badRequest().build();
  }

  @PostMapping("api/order/cancel")
  public ResponseEntity<?> deleteOrder(@RequestBody OrderForm orderForm) {
    if(orderService.deleteOrder(orderForm.getOrder_id())) return ResponseEntity.ok().build();
    return ResponseEntity.badRequest().build();
  }

  @PostMapping("api/order/amend")
  public ResponseEntity<?> amendOrder(@RequestBody OrderForm orderForm) {
    int order_id = orderService.amendOrder(orderForm.getOrder_id(), orderForm.getQuantity());
    if(order_id != -1) {
      Order order = orderService.getOrderById(order_id);
      String market = itemService.getmarketByItemId(order.getItem_id());
      OrderType orderType = orderService.getOrderTypeById(order_id);
      orderSuccess.updateOrderStatus(order.getOrder_id(), orderType.getQuantity(), order.getItem_id(), order.getAccount_id(), order.getPurchase_type(), market, orderType.getLimit_price());
      return ResponseEntity.ok().build();
    }
    return ResponseEntity.badRequest().build();
  }

  @PostMapping("api/order/log")
  public ResponseEntity<?> getOrdersByDate(@RequestBody OrderForm orderForm) {
    return ResponseEntity.ok(orderService.getOrdersByDate(orderForm.getAccount_id(), orderForm.getDate()));
  }

  @PostMapping("api/order/pendings")
  public ResponseEntity<?> getPendingOrders(@RequestBody OrderForm orderForm) {
    return ResponseEntity.ok(orderService.getPendingOrders(orderForm.getAccount_id()));
  }

}
