package com.caudbdesign.dbTeamProject.Admin.ChangePrice;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PriceRepository {

  private final JdbcTemplate jdbcTemplate;

  public void updatePrice(int itemId, float opening_price, float closing_price, float current_price) {
    String sql = "CALL UpdateItemPriceInfo(?, ?, ?)";
    jdbcTemplate.update(sql, itemId, opening_price, closing_price);
  }

  public void updateCurrentPrice(int itemId, float current_price) {
    String sql = "UPDATE CurrentPrice SET current_price = ?, updated_at = NOW() WHERE item_id = ?";
    jdbcTemplate.update(sql, current_price, itemId);
  }

  //daily high와 low를 업데이트하는 함수
  public void updateDailyHighLow(int itemId, float current_price) {
    String sql = "UPDATE ItemPriceInfo SET daily_high = ?, daily_low = ? WHERE item_id = ?";
    jdbcTemplate.update(sql, current_price, current_price, itemId);
  }

  public void updateWeek52HighLow(int itemId, float current_price) {
    String sql = "UPDATE ItemPriceInfo SET week_52_high = ?, week_52_low = ? WHERE item_id = ?";
    jdbcTemplate.update(sql, current_price, current_price, itemId);
  }

  public void updateHistoricalHighLow(int itemId, float current_price) {
    String sql = "UPDATE ItemPriceInfo SET historical_high = ?, historical_low = ? WHERE item_id = ?";
    jdbcTemplate.update(sql, current_price, current_price, itemId);
  }


}
