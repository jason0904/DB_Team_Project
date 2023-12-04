package com.caudbdesign.dbTeamProject.Account;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class AccountForm {

  //Account와 AccountType을 합친 Form
  private Integer account_id;
  private String Available_offer_Type;
  private Integer uID;
  private String account_number;
  private String account_type;
  private float stock_value;
  private String status;
  private String created_at;

}
