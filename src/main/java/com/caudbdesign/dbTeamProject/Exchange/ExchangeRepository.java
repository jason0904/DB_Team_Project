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

  public void save(CurrencyExchange currencyExchange) {
    jdbcTemplate.update("INSERT INTO CurrencyExchange (account_id, base_currency, foreign_currency, base_amount, foreign_amount, exchange_time) VALUES (?, ?, ?, ?, ?, ?)",
        currencyExchange.getAccount_id(), currencyExchange.getBase_currency(), currencyExchange.getForeign_currency(), currencyExchange.getBase_amount(), currencyExchange.getForeign_amount(), currencyExchange.getExchange_time());
  }

  public CurrentExchangeRate selectRate(String base_currency, String foreign_currency) {
    String sql = "select * from CurrentExchangeRate where base_currency = ? and foreign_currency = ?";
    RowMapper<CurrentExchangeRate> rowMapper = new BeanPropertyRowMapper<>(CurrentExchangeRate.class);
    return jdbcTemplate.queryForObject(sql, rowMapper, base_currency, foreign_currency);
  }
}
