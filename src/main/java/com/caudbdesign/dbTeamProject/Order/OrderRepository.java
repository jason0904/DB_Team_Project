package com.caudbdesign.dbTeamProject.Order;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class OrderRepository {

  private final JdbcTemplate jdbcTemplate;

  public void insertOrder(Order order, OrderType orderType) {
    String sql = "INSERT INTO `Order` (account_id, item_id, purchase_type, order_status, created_at, success_at) VALUES (?, ?, ?, ?, ?, ?)";
    jdbcTemplate.update(sql, order.getAccount_id(), order.getItem_id(), order.getPurchase_type(), order.getOrder_status(), order.getCreated_at(), order.getSuccess_at());
    if(order.getPurchase_type().equals("SellOrder")) {
      String sql2 = "INSERT INTO SellOrder (order_id, quantity, order_type, limit_price) VALUES (?, ?, ?, ?)";
      jdbcTemplate.update(sql2, orderType.getOrder_id(), orderType.getQuantity(), orderType.getOrder_type(), orderType.getLimit_price());
    } else if(order.getPurchase_type().equals("BuyOrder")) {
      String sql2 = "INSERT INTO BuyOrder (order_id, quantity, order_type, limit_price) VALUES (?, ?, ?, ?)";
      jdbcTemplate.update(sql2, orderType.getOrder_id(), orderType.getQuantity(), orderType.getOrder_type(), orderType.getLimit_price());
    }
  }

  public Order findOrderById(Integer order_id) {
    String sql = "SELECT * FROM `Order` WHERE order_id = ?";
    RowMapper<Order> rowMapper = new BeanPropertyRowMapper<>(Order.class);
    return jdbcTemplate.queryForObject(sql, rowMapper, order_id);
  }

  public OrderType findOrderTypeById(Integer order_id) {
    String sql = "SELECT * FROM `Order` WHERE order_id = ?";
    RowMapper<OrderType> rowMapper = new BeanPropertyRowMapper<>(OrderType.class);
    return jdbcTemplate.queryForObject(sql, rowMapper, order_id);
  }

  public void deleteOrder(Integer order_id, String Order_type) {
    String sql = "DELETE FROM `Order` WHERE order_id = ?";
    jdbcTemplate.update(sql, order_id);
    if(Order_type.equals("SellOrder")) {
      String sql2 = "DELETE FROM SellOrder WHERE order_id = ?";
      jdbcTemplate.update(sql2, order_id);
    } else if(Order_type.equals("BuyOrder")) {
      String sql2 = "DELETE FROM BuyOrder WHERE order_id = ?";
      jdbcTemplate.update(sql2, order_id);
    }
  }


  public void updateOrderStatus(int order_id) {
    String sql = "CALL FinalizeOrder(?)";
    jdbcTemplate.update(sql, order_id);
  }







}
