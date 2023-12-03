package com.caudbdesign.dbTeamProject.Order;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
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
    String sql = "INSERT INTO Order (account_id, item_id, purchase_type, order_status) VALUES (?, ?, ?, ?)";
    jdbcTemplate.update(sql, order.getAccount_id(), order.getItem_id(), order.getPurchase_type(), order.getOrder_status());
    String type = orderType.getOrder_type();
    if(type.equals("SellOrder")) {
      sql = "INSERT INTO SellOrder (order_id, quantity, left_quantity, limit_price) VALUES (?, ?, ?, ?)";
      jdbcTemplate.update(sql, order.getOrder_id(), orderType.getQuantity(), orderType.getLeft_quantity(), orderType.getLimit_price());
    }
    else if(type.equals("BuyOrder")) {
      sql = "INSERT INTO BuyOrder (order_id, quantity, left_quantity, limit_price) VALUES (?, ?, ?, ?)";
      jdbcTemplate.update(sql, order.getOrder_id(), orderType.getQuantity(), orderType.getLeft_quantity(), orderType.getLimit_price());
    }
  }



}
