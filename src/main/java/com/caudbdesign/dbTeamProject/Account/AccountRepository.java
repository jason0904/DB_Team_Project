package com.caudbdesign.dbTeamProject.Account;

import java.util.ArrayList;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
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
}
