package com.caudbdesign.dbTeamProject.Account;

import java.util.ArrayList;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Getter
@RequiredArgsConstructor
public class AccountService {

  private final AccountRepository accountRepository;

  public ArrayList<String> getAccountNumbersByUID(int uid) {
    return accountRepository.getAccountNumbersByUID(uid);
  }

}
