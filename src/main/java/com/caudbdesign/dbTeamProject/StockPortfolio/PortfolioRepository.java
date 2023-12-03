package com.caudbdesign.dbTeamProject.StockPortfolio;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PortfolioRepository {

  private final JdbcTemplate jdbcTemplate;

  public StockPortfolio selectPortfolio(Integer account_id) {
    String sql = "select * from StockPortfolio where account_id = ?";
    RowMapper<StockPortfolio> rowMapper = new BeanPropertyRowMapper<>(StockPortfolio.class);
    return jdbcTemplate.queryForObject(sql, rowMapper, account_id);
  }

}
