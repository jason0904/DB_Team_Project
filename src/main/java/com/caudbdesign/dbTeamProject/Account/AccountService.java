package com.caudbdesign.dbTeamProject.Account;

import java.util.ArrayList;
import java.util.Optional;
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

  public Optional<Account> getAccountByAccountNumber(String accountNumber) {
    return accountRepository.getAccountByAccountNumber(accountNumber);
  }

  public AccountType getAccountTypeByAccountId(int account_id) {
    String account_type = accountRepository.getAccountTypeByAccountId(account_id);
    return accountRepository.getGeneralAccountByAccountIdAndAccountType(account_id, account_type);
  }

  public ArrayList<String> getAccountTypesByUID(int uid) {
    ArrayList<String> accountNumbers = accountRepository.getAccountNumbersByUID(uid);
    ArrayList<String> accountTypes = new ArrayList<>();
    for (String accountNumber : accountNumbers) {
      Optional<Account> accountOptional = accountRepository.getAccountByAccountNumber(
          accountNumber);
      if (accountOptional.isPresent()) {
        accountTypes.add(accountOptional.get().getAccount_type());
      } else {
        accountTypes.add("null");
      }
    }
    return accountTypes;
  }

  public AccountForm getAccountForm(Account account, AccountType accountType) {
    AccountForm accountForm = new AccountForm();
    accountForm.setAccount_id(account.getAccount_id());
    accountForm.setAccount_number(account.getAccount_number());
    accountForm.setAccount_type(account.getAccount_type());
    accountForm.setAvailable_offer_Type(accountType.getAvailable_offer_Type());
    accountForm.setUID(account.getUID());
    accountForm.setStatus(account.getStatus());
    accountForm.setCreated_at(account.getCreated_at());
    accountForm.setStock_value(account.getStock_value());
    return accountForm;
  }

  public boolean accountLogin(int account_id, String password_hash) {
    return accountRepository.accountLogin(account_id,password_hash);
  }

  public Account getAccountByAccountId(int account_id) {
    return accountRepository.getAccountByAccountId(account_id);
  }

}
