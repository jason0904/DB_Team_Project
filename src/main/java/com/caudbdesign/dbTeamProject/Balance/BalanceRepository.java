package com.caudbdesign.dbTeamProject.Balance;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BalanceRepository {

  private final JdbcTemplate jdbcTemplate;

  public Balance selectBalance(Integer account_id) {
    String sql = "select * from Balance where account_id = ?";
    RowMapper<Balance> rowMapper = new BeanPropertyRowMapper<>(Balance.class);
    return jdbcTemplate.queryForObject(sql, rowMapper, account_id);
  }

  public void updateBalance(Integer account_id, float Total_Balance, float KRW_Balance, float USD_Balance) {
    jdbcTemplate.update("UPDATE Balance SET Total_Balance = ?, KRW_Balance = ?, USD_Balance = ? WHERE account_id = ?",
        Total_Balance, KRW_Balance, USD_Balance, account_id);
  }





}
