package com.caudbdesign.dbTeamProject.Account;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@RequiredArgsConstructor
public class AccountType {

  private Integer account_id;
  private String Available_offer_Type;

}
