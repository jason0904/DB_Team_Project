package com.caudbdesign.dbTeamProject.Account;


import java.sql.Timestamp;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Getter
@Setter
public class AccountPassword {

  private int account_id;
  private int password_attempt;
  private String password_hash;
  private Timestamp updated_at;
}
