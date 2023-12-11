package com.caudbdesign.dbTeamProject.ItemPortfolio;

import com.caudbdesign.dbTeamProject.Item.Item;
import java.util.ArrayList;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PortfolioRepository {

  private final JdbcTemplate jdbcTemplate;

  public ItemPortfolio selectPortfolio(Integer account_id) {
    String sql = "select * from ItemPortfolio where account_id = ?";
    RowMapper<ItemPortfolio> rowMapper = new BeanPropertyRowMapper<>(ItemPortfolio.class);
    return jdbcTemplate.queryForObject(sql, rowMapper, account_id);
  }


  public void updatePortfolio(int item_id, int quantity, int account_id) {
    String sql = "update ItemPortfolio set quantity = ? where item_id = ? and account_id = ?";
    jdbcTemplate.update(sql, quantity, item_id, account_id);
  }

  public void updateStockPortfolioTotalPurchasePrice(int item_id, int account_id, double totalPurchasePrice) {
    String sql = "CALL UpdateStockPortfolioTotalPurchasePrice(?, ?, ?)";
    jdbcTemplate.update(sql, account_id, item_id, totalPurchasePrice);
  }

}
