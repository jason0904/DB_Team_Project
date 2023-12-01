package com.caudbdesign.dbTeamProject.Account;

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
  private Integer uID;
  private String account_number;
  private String account_type;
  private float stock_value;
  private String status;
  private String created_at;

}
