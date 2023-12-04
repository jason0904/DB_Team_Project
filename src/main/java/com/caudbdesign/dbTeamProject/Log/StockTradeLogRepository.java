package com.caudbdesign.dbTeamProject.Log;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
@Setter
@Getter
@RequiredArgsConstructor
public class StockTradeLogRepository {

  private final JdbcTemplate jdbcTemplate;

  public StockTradeLog selectTradeLogByOrderID(int Order_id, String trade_time) {
    //검색 당일과 같을경우
    String sql = "SELECT * FROM StockTradeLog WHERE order_id = ? AND DATE(trade_time) = ?;";
    RowMapper<StockTradeLog> rowMapper = new BeanPropertyRowMapper<>(StockTradeLog.class);
    return jdbcTemplate.queryForObject(sql, rowMapper, Order_id, trade_time);
  }

}
