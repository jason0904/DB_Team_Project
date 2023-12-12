package com.caudbdesign.dbTeamProject.Order;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Repository
public class OrderRepository {

  private final JdbcTemplate jdbcTemplate;

  @Transactional
  public int insertOrder(Order order, OrderType orderType) {
    String sql = "INSERT INTO `Order` (account_id, item_id, purchase_type, order_status, created_at) VALUES (?, ?, ?, ?, NOW())";
    jdbcTemplate.update(sql, order.getAccount_id(), order.getItem_id(), order.getPurchase_type(), order.getOrder_status());
    int order_id = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
    if(order.getPurchase_type().equalsIgnoreCase("sellorder")) {
      String sql2 = "INSERT INTO SellOrder (order_id, quantity, order_type, limit_price) VALUES (?, ?, ?, ?)";
      jdbcTemplate.update(sql2, order_id, orderType.getQuantity(), orderType.getOrder_type(), orderType.getLimit_price());
    } else if(order.getPurchase_type().equalsIgnoreCase("buyorder")) {
      String sql2 = "INSERT INTO BuyOrder (order_id, quantity, order_type, limit_price) VALUES (?, ?, ?, ?)";
      jdbcTemplate.update(sql2, order_id, orderType.getQuantity(), orderType.getOrder_type(), orderType.getLimit_price());
    }
    return order_id;
  }

  public Order findOrderById(Integer order_id) {
    String sql = "SELECT * FROM `Order` WHERE order_id = ?";
    RowMapper<Order> rowMapper = new BeanPropertyRowMapper<>(Order.class);
    return jdbcTemplate.queryForObject(sql, rowMapper, order_id);
  }

  public OrderType findOrderTypeById(Integer order_id) {
    if(findOrderById(order_id).getPurchase_type().equalsIgnoreCase("sellorder")) {
      String sql = "SELECT * FROM SellOrder WHERE order_id = ?";
      RowMapper<OrderType> rowMapper = new BeanPropertyRowMapper<>(OrderType.class);
      return jdbcTemplate.queryForObject(sql, rowMapper, order_id);
    } else {
      String sql = "SELECT * FROM BuyOrder WHERE order_id = ?";
      RowMapper<OrderType> rowMapper = new BeanPropertyRowMapper<>(OrderType.class);
      return jdbcTemplate.queryForObject(sql, rowMapper, order_id);
    }
  }

  public void deleteOrder(Integer order_id) {
    String sql = "Update `Order` SET order_status = 'cancelled' WHERE order_id = ?";
    jdbcTemplate.update(sql, order_id);
  }


  public void updateOrderStatus(int order_id) {
    String sql = "CALL FinalizeOrder(?)";
    jdbcTemplate.update(sql, order_id);
  }

  public boolean checkOrderValidity(int item_id, int quantity, int account_id) {
    String sql = "SELECT CheckOrderValidity(?, ?, ?)";
    return Boolean.TRUE.equals(
        jdbcTemplate.queryForObject(sql, Boolean.class, item_id, quantity, account_id));
  }

  public List<Order> getOrdersByDate(int account_id, String date) {
    String sql = "SELECT * FROM `Order` WHERE account_id = ? AND DATE(created_at) = ?";
    RowMapper<Order> rowMapper = new BeanPropertyRowMapper<>(Order.class);
    return jdbcTemplate.query(sql, rowMapper, account_id, date);
  }


  public List<Order> getPendingOrders(int account_id) {
    String sql = "SELECT * FROM `Order` WHERE account_id = ? AND order_status = 'pending'";
    RowMapper<Order> rowMapper = new BeanPropertyRowMapper<>(Order.class);
    return jdbcTemplate.query(sql, rowMapper, account_id);
  }




}
