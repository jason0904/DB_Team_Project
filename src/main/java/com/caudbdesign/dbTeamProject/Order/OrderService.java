package com.caudbdesign.dbTeamProject.Order;


import com.caudbdesign.dbTeamProject.Item.ItemRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

  private final OrderRepository orderRepository;

  public boolean createOrder(Order order, OrderType orderType) {
    if(!orderRepository.checkOrderValidity(order.getItem_id(), orderType.getQuantity(), order.getAccount_id())) return false;
    int order_id = orderRepository.insertOrder(order, orderType);
    order.setOrder_id(order_id);
    return true;
  }

  public boolean deleteOrder(int order_id) {
    Order order = orderRepository.findOrderById(order_id);
    if(order.getOrder_status().equals("success")) return false;
    orderRepository.deleteOrder(order_id);
    return true;
  }

  public int amendOrder(int order_id, int quantity) {
    Order order = orderRepository.findOrderById(order_id);
    OrderType orderType = orderRepository.findOrderTypeById(order_id);
    if(order.getOrder_status().equals("success") || order.getOrder_status().equals("cancelled")) return -1;
    deleteOrder(order_id);
    orderType.setQuantity(quantity);
    return orderRepository.insertOrder(order, orderType);
  }

  public List<Order> getOrdersByDate(int account_id, String date) {
    return orderRepository.getOrdersByDate(account_id, date);
  }

  public Order getOrderById(int order_id) {
    return orderRepository.findOrderById(order_id);
  }

  public OrderType getOrderTypeById(int order_id) {
    return orderRepository.findOrderTypeById(order_id);
  }

  public List<OrderForm> getPendingOrders(int account_id) {
    List<Order> orders = orderRepository.getPendingOrders(account_id);
    List<OrderForm> orderForms = new ArrayList<>();
    for(Order order : orders) {
      OrderForm orderForm = new OrderForm();
      orderForm.setOrder_id(order.getOrder_id());
      orderForm.setAccount_id(order.getAccount_id());
      orderForm.setItem_id(order.getItem_id());
      orderForm.setPurchase_type(order.getPurchase_type());
      orderForm.setOrder_status(order.getOrder_status());
      orderForm.setCreated_at(order.getCreated_at());
      orderForm.setQuantity(orderRepository.findOrderTypeById(order.getOrder_id()).getQuantity());
      orderForm.setOrder_type(orderRepository.findOrderTypeById(order.getOrder_id()).getOrder_type());
      orderForm.setLimit_price(orderRepository.findOrderTypeById(order.getOrder_id()).getLimit_price());
      orderForms.add(orderForm);
    }
    return orderForms;
  }

}
