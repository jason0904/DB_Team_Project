package com.caudbdesign.dbTeamProject.Account;

import java.util.ArrayList;
import java.util.Optional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
@Getter
public class AccountRepository {

  private final JdbcTemplate jdbcTemplate;


  public ArrayList<String> getAccountNumbersByUID(int uid) {
    ArrayList<String> accountNumbers = new ArrayList<>();
    String sql = "select account_number from Account where uid = ?";
    jdbcTemplate.query(sql, (rs, rowNum) -> accountNumbers.add(rs.getString("account_number")), uid);
    return accountNumbers;
  }

  public Optional<Account> getAccountByAccountNumber(String accountNumber) {
    String sql = "select * from Account where account_number = ?";
    RowMapper<Account> rowMapper = new BeanPropertyRowMapper<>(Account.class);
    Account account = jdbcTemplate.queryForObject(sql, rowMapper, accountNumber);
    if(account == null) {
      return Optional.empty();
    }
    return Optional.of(account);
  }

}
