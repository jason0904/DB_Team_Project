package com.caudbdesign.dbTeamProject.Exchange;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@Getter
public class ExchangeRepository {

  private final JdbcTemplate jdbcTemplate;

  public boolean exchange(int account_id, String base_currency, String foreign_currency, float amount) {
    float exchange_rate = selectRate(base_currency, foreign_currency).getCurrent_exchange_rate();
    String sql = "CALL ExchangeCurrency(?, ?, ?, ?, ?)";
    return Boolean.TRUE.equals(
        jdbcTemplate.queryForObject(sql, boolean.class, account_id, base_currency, foreign_currency,
            amount, exchange_rate));
  }

  public CurrentExchangeRate selectRate(String base_currency, String foreign_currency) {
    String sql = "select * from CurrentExchangeRate where base_currency = ? and foreign_currency = ?";
    RowMapper<CurrentExchangeRate> rowMapper = new BeanPropertyRowMapper<>(CurrentExchangeRate.class);
    return jdbcTemplate.queryForObject(sql, rowMapper, base_currency, foreign_currency);
  }

  public float selectRateByDay(String base_currency, String foreign_currency, String day) {
    String sql = "select * from ExchangeRateHistory where base_currency = ? and foreign_currency = ? and created_at = ?";
    RowMapper<ExchangeRateHistory> rowMapper = new BeanPropertyRowMapper<>(ExchangeRateHistory.class);
    ExchangeRateHistory exchangeRateHistory = jdbcTemplate.queryForObject(sql, rowMapper, base_currency, foreign_currency, day);
    assert exchangeRateHistory != null;
    return exchangeRateHistory.getExchange_rate();
  }
}
