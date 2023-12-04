package com.caudbdesign.dbTeamProject.Item;

import java.util.ArrayList;
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

  public ItemPrice_Info getItemPriceInfoByItemId(int itemId) {
    String sql = "SELECT * FROM ItemPrice_Info WHERE item_id = ?;";
    RowMapper<ItemPrice_Info> rowMapper = new BeanPropertyRowMapper<>(ItemPrice_Info.class);
    return jdbcTemplate.queryForObject(sql, rowMapper, itemId);
  }



}
