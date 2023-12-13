package com.caudbdesign.dbTeamProject.ItemPortfolio;

import com.caudbdesign.dbTeamProject.Item.Item;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PortfolioRepository {

  private final JdbcTemplate jdbcTemplate;

  public List<ItemPortfolio> selectPortfolio(Integer account_id) {
    String sql = "select * from ItemPortfolio where account_id = ?";
    RowMapper<ItemPortfolio> rowMapper = new BeanPropertyRowMapper<>(ItemPortfolio.class);
    return jdbcTemplate.query(sql, rowMapper, account_id);
  }

  public ItemPortfolio selectPortfoliobyItemId(Integer account_id, Integer item_id) {
    String sql = "select * from ItemPortfolio where account_id = ? and item_id = ?";
    RowMapper<ItemPortfolio> rowMapper = new BeanPropertyRowMapper<>(ItemPortfolio.class);
    return jdbcTemplate.queryForObject(sql, rowMapper, account_id, item_id);
  }


  public void updatePortfolio(int item_id, int quantity, int account_id) {
    String sql = "update ItemPortfolio set quantity = ? where item_id = ? and account_id = ?";
    jdbcTemplate.update(sql, quantity, item_id, account_id);
  }

  public void updateStockPortfolioTotalPurchasePrice(int item_id, int account_id, double totalPurchasePrice) {
    String sql = "CALL UpdateStockPortfolioTotalPurchasePrice(?, ?, ?)";
    jdbcTemplate.update(sql, account_id, item_id, totalPurchasePrice);
  }

  public float totalPriceSum(Integer account_id) {
    String sql = "select sum(total_purchase_price*quantity) from ItemPortfolio where account_id = ?;";
    float totalPriceSum = jdbcTemplate.queryForObject(sql, BigDecimal.class, account_id).floatValue();
    return totalPriceSum;
  }

  public float totalValue(Integer account_id) {
    String sql = "select sum(current_price*quantity) from ItemPortfolio where account_id = ?;";
    float totalValue = jdbcTemplate.queryForObject(sql, BigDecimal.class, account_id).floatValue();
    return totalValue;
  }

  public float calculateTotalReturnInKrw(Integer account_id, Integer item_id) {
    String sql = "select CalculateInvestmentReturn(?, ?)";
    return jdbcTemplate.queryForObject(sql, BigDecimal.class, account_id, item_id).floatValue();
  }

  public List<Integer> selectAllItemIdInPortfolio() {
    String sql = "select item_id from ItemPortfolio";
    RowMapper<Integer> rowMapper = new BeanPropertyRowMapper<>(Integer.class);
    return jdbcTemplate.query(sql, rowMapper);
  }
  public void updateCurrentPrice(int item_id, float current_price) {
    String sql = "update ItemPortfolio set current_price = ? where item_id = ?";
    jdbcTemplate.update(sql, current_price, item_id);
  }

  public List<Integer> selectItemIdInPortfolioByAccountId(Integer account_id) {
    String sql = "select item_id from ItemPortfolio where account_id = ?";
    RowMapper<Integer> rowMapper = new BeanPropertyRowMapper<>(Integer.class);
    return jdbcTemplate.query(sql, rowMapper, account_id);
  }

}
