package com.caudbdesign.dbTeamProject.Item;

import com.caudbdesign.dbTeamProject.Item.Stock.AnalystInfo;
import com.caudbdesign.dbTeamProject.Item.Stock.FinancialStatements;
import com.caudbdesign.dbTeamProject.Item.Stock.ItemInfoForm;
import com.caudbdesign.dbTeamProject.Item.Stock.StockData;
import com.caudbdesign.dbTeamProject.Item.Stock.StockRate;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
@Getter
@RequiredArgsConstructor
public class ItemRepository {

  private final JdbcTemplate jdbcTemplate;

  public List<Item> getItemsbySearch(String search) {
    String sql = "SELECT * FROM Item WHERE name LIKE ?;";
    RowMapper<Item> rowMapper = new BeanPropertyRowMapper<>(Item.class);
    return jdbcTemplate.query(sql, rowMapper, "%" + search + "%");
  }

  public Optional<Item> getItemByItemId(int itemId) {
    String sql = "SELECT * FROM Item WHERE item_id = ?;";
    RowMapper<Item> rowMapper = new BeanPropertyRowMapper<>(Item.class);
    Item item = jdbcTemplate.queryForObject(sql, rowMapper, itemId);
    if (item == null) {
      return Optional.empty();
    }
    return Optional.of(item);
  }

  public ItemPriceInfo getItemPriceInfoByItemId(int itemId) {
    String sql = "SELECT * FROM ItemPriceInfo WHERE item_id = ?;";
    RowMapper<ItemPriceInfo> rowMapper = new BeanPropertyRowMapper<>(ItemPriceInfo.class);
    return jdbcTemplate.queryForObject(sql, rowMapper, itemId);
  }

  public StockRate getStockRateByItemId(int itemId) {
    String sql = "SELECT * FROM StockRate WHERE item_id = ?;";
    RowMapper<StockRate> rowMapper = new BeanPropertyRowMapper<>(StockRate.class);
    return jdbcTemplate.queryForObject(sql, rowMapper, itemId);
  }

  public FinancialStatements getFinancialStatementsByItemId(int itemId, String report_period) {
    String sql = "SELECT * FROM FinancialStatements WHERE item_id = ? and report_period = ?;";
    RowMapper<FinancialStatements> rowMapper = new BeanPropertyRowMapper<>(FinancialStatements.class);
    return jdbcTemplate.queryForObject(sql, rowMapper, itemId, report_period);
  }

  public AnalystInfo getAnalystInfoByAnalystId(int id) {
    String sql = "SELECT * FROM AnalystInfo WHERE analyst_id= ?;";
    RowMapper<AnalystInfo> rowMapper = new BeanPropertyRowMapper<>(AnalystInfo.class);
    return jdbcTemplate.queryForObject(sql, rowMapper, id);
  }

  public CurrentPrice getCurrentPriceByItemId(int itemId) {
    String sql = "SELECT * FROM CurrentPrice WHERE item_id = ?;";
    RowMapper<CurrentPrice> rowMapper = new BeanPropertyRowMapper<>(CurrentPrice.class);
    return jdbcTemplate.queryForObject(sql, rowMapper, itemId);
  }

  public String getmarketByItemId(int itemId) {
    String sql = "SELECT market FROM Item WHERE item_id = ?;";
    return jdbcTemplate.queryForObject(sql, String.class, itemId);
  }

  public String getNameByItemId(int itemId) {
    String sql = "select name from Item where item_id = ?;";
    return jdbcTemplate.queryForObject(sql, String.class, itemId);
  }


  public float getOnlyCurrentPriceByItemId(int itemId) {
    String sql = "select current_price from CurrentPrice where item_id = ?";
    return jdbcTemplate.queryForObject(sql, Float.class, itemId);
  }

  public float getOnlyStartpriceByItemId(int itemId) {
    String sql = "select opening_price from ItemPriceInfo where item_id = ?;";
    return jdbcTemplate.queryForObject(sql, BigDecimal.class, itemId).floatValue();
  }


}
