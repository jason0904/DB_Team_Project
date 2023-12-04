package com.caudbdesign.dbTeamProject.Order;

import java.util.ArrayList;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.relational.core.sql.In;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Getter
@Repository
public class OrderRepository {

  private final JdbcTemplate jdbcTemplate;

  public Order selectOrder(Integer order_id) {
    String sql = "select * from Order where order_id = ?";
    RowMapper<Order> rowMapper = new BeanPropertyRowMapper<>(Order.class);
    return jdbcTemplate.queryForObject(sql, rowMapper, order_id);
  }

  public ArrayList<Integer> selectOrderIdByAccountId(Integer account_id) {
    String sql = "select order_id from Order where account_id = ?";
    RowMapper<Integer> rowMapper = new BeanPropertyRowMapper<>(Integer.class);
    return (ArrayList<Integer>) jdbcTemplate.query(sql, rowMapper, account_id);
  }

  public OrderType selectOrderType(Integer order_id, String type) {
    String sql;
    if(type.equals("SellOrder")) {
      sql = "select * from SellOrder where order_id = ?";
    }
    else {
      sql = "select * from BuyOrder where order_id = ?";
    }
    RowMapper<OrderType> rowMapper = new BeanPropertyRowMapper<>(OrderType.class);
    return jdbcTemplate.queryForObject(sql, rowMapper, order_id);
  }

  public void DeleteOrder(Integer order_id) {
    Order order = selectOrder(order_id);
    String sql = "delete from Order where order_id = ?";
    jdbcTemplate.update(sql, order_id);
  }

  public void DeleteOrderType(Integer order_id, String type) {
    String sql;
    if(type.equals("SellOrder")) {
      sql = "delete from SellOrder where order_id = ?";
    }
    else {
      sql = "delete from BuyOrder where order_id = ?";
    }
    jdbcTemplate.update(sql, order_id);
  }

  public void save(Order order, OrderType orderType) {
    String sql = "INSERT INTO Order (account_id, item_id, purchase_type, order_status, created_at) VALUES (?, ?, ?, ?, ?)";
    jdbcTemplate.update(sql, order.getAccount_id(), order.getItem_id(), order.getPurchase_type(), order.getOrder_status(), System.currentTimeMillis());
    String type = orderType.getOrder_type();
    if(type.equals("SellOrder")) {
      sql = "INSERT INTO SellOrder (order_id, quantity, left_quantity, limit_price) VALUES (?, ?, ?, ?)";
      jdbcTemplate.update(sql, order.getOrder_id(), orderType.getQuantity(), orderType.getLeft_quantity(), orderType.getLimit_price());
    }
    else if(type.equals("BuyOrder")) {
      sql = "INSERT INTO BuyOrder (order_id, quantity, left_quantity, limit_price) VALUES (?, ?, ?, ?)";
      jdbcTemplate.update(sql, order.getOrder_id(), orderType.getQuantity(), orderType.getLeft_quantity(), orderType.getLimit_price());
    }
    //StockTradeLog에도 그 기록 추가해야함.
    sql = "INSERT INTO StockTradeLog (trade_id, order_id, purchase_type, executed_quantity, executed_price, trade_time) VALUES (?, ?, ?, ?, ?, ?)";
    jdbcTemplate.update(sql, order.getOrder_id(), order.getOrder_id(), order.getPurchase_type(), orderType.getQuantity(), orderType.getLimit_price() * orderType.getQuantity(), System.currentTimeMillis());

  }

  public ArrayList<OrderForm> showPendingInfo(Integer account_id) {
    String sql = "select * from Order where account_id = ? and order_status = 'Pending'";
    RowMapper<Order> rowMapper = new BeanPropertyRowMapper<>(Order.class);
    ArrayList<Order> orders = (ArrayList<Order>) jdbcTemplate.query(sql, rowMapper, account_id);
    ArrayList<OrderForm> result = new ArrayList<>();
    for(Order order : orders) {
      OrderType orderType = selectOrderType(order.getOrder_id(), order.getPurchase_type());
      OrderForm orderForm = new OrderForm();
      orderForm.setOrder_id(order.getOrder_id());
      orderForm.setAccount_id(order.getAccount_id());
      orderForm.setItem_id(order.getItem_id());
      orderForm.setPurchase_type(order.getPurchase_type());
      orderForm.setOrder_status(order.getOrder_status());
      orderForm.setQuantity(orderType.getQuantity());
      orderForm.setLeft_quantity(orderType.getLeft_quantity());
      orderForm.setOrder_type(orderType.getOrder_type());
      orderForm.setLimit_price(orderType.getLimit_price());
      result.add(orderForm);
    }
    return result;
  }



}
