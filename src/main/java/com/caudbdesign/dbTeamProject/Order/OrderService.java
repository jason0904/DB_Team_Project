package com.caudbdesign.dbTeamProject.Order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class OrderService {

  private final OrderRepository orderRepository;

  public Integer amend(Integer order_id, float price) {

    Order order = orderRepository.selectOrder(order_id);
    OrderType orderType = orderRepository.selectOrderType(order_id, order.getPurchase_type());
    if(order.getOrder_status().equals("Success")) {
      return null;
    }
    if(price < 0) {
      return null;
    }
    orderType.setLimit_price(price);
    orderRepository.DeleteOrder(order_id);
    orderRepository.DeleteOrderType(order_id, order.getPurchase_type());
    orderRepository.save(order, orderType);
    Order order1 = orderRepository.selectOrder(order_id);
    return order1.getOrder_id();
  }

  public boolean cancel(Integer order_id) {
    Order order = orderRepository.selectOrder(order_id);
    if(order.getOrder_status().equals("Success")) {
      return false;
    }
    orderRepository.DeleteOrder(order_id);
    orderRepository.DeleteOrderType(order_id, order.getPurchase_type());
    return true;
  }

  public void order(Order order, OrderType orderType) {
    orderRepository.save(order, orderType);
  }

  public Order selectOrder(Integer order_id) {
    return orderRepository.selectOrder(order_id);
  }

}
