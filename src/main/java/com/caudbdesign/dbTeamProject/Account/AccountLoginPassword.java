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
public class AccountLoginPassword {

  private int account_id;
  private String password_hash;
  private Timestamp updated_at;
}

