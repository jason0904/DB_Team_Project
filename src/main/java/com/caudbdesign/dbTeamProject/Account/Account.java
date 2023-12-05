package com.caudbdesign.dbTeamProject.Account;

import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@RequiredArgsConstructor
public class Account {

  private Integer account_id;
  private Integer uid;
  private String account_number;
  private String account_type;
  private float stock_value;
  private String status;
  private Timestamp created_at;
  private Timestamp updated_at;

}
