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

  public boolean isAccountExist(int account_id) {
    String sql = "select count(*) from Account where account_id = ?";
    Integer count = jdbcTemplate.queryForObject(sql, Integer.class, account_id);
    if(count == null) {
      return false;
    }
    return count != 0;
  }

  public String getAccountTypeByAccountId(int account_id) {
    String sql = "select account_type from Account where account_id = ?";
    return jdbcTemplate.queryForObject(sql, String.class, account_id);
  }

  public Account getAccountByAccountId(int account_id) {
    String sql = "select * from Account where account_id = ?";
    RowMapper<Account> rowMapper = new BeanPropertyRowMapper<>(Account.class);
    Account account = jdbcTemplate.queryForObject(sql, rowMapper, account_id);
    assert account != null;
    return account;
  }

  public AccountType getGeneralAccountByAccountIdAndAccountType(int account_id, String account_type) {
    String sql;
    if(account_type.equals("GeneralAccount")) {
      sql = "select * from GeneralAccount where account_id = ?";
    }
    else sql = "select * from GoldAccount where account_id = ?";
    RowMapper<AccountType> rowMapper = new BeanPropertyRowMapper<>(AccountType.class);
    return jdbcTemplate.queryForObject(sql, rowMapper, account_id);
  }

  public boolean accountLogin (int account_id, String password) {
    String sql = "select password_hash from AccountLoginPassword where account_id = ?";
    String password_hash = jdbcTemplate.queryForObject(sql, String.class, account_id);
    assert password_hash != null;
    return password_hash.equals(password);
  }

  public void updateLoginTime(int account_id) {
    String sql = "update AccountLoginPassword set updated_at = now() where account_id = ?";
    jdbcTemplate.update(sql, account_id);
  }

  public void insertLoginLog(int account_id, String status) {
    String sql = "insert into AccountLoginLog(account_id, login_time, login_status) values(?, now(), ?)";
    jdbcTemplate.update(sql, account_id, status);
  }

  public boolean isLoginLocked(int account_id) {
    String sql = "select status from Account where account_id = ?";
    String status = jdbcTemplate.queryForObject(sql, String.class, account_id);
    assert status != null;
    return status.equals("locked");
  }

  public Integer getLoginAttempt(int account_id) {
    String sql = "select login_attempt from AccountLoginMeta where account_id = ?";
    return jdbcTemplate.queryForObject(sql, Integer.class, account_id);
  }

  public void updateStatusByAccountId(int account_id, String status) {
    String sql = "update Account set status = ? where account_id = ?";
    jdbcTemplate.update(sql, status, account_id);
  }

}
