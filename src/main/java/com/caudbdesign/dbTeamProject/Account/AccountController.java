package com.caudbdesign.dbTeamProject.Account;


import java.util.Optional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@Getter
@RequiredArgsConstructor
public class AccountController {

  private final AccountService accountService;

  @PostMapping("/api/account")
  @CrossOrigin
  public ResponseEntity<?> getAccountInfo(@RequestBody Account account) {
    Optional<Account> accountOptional = accountService.getAccountByAccountNumber(account.getAccount_number());
    if(accountOptional.isEmpty()) {
      return ResponseEntity.badRequest().body("Account not found");
    }
    AccountType accountType = accountService.getAccountTypeByAccountId(accountOptional.get().getAccount_id());
    AccountForm accountForm = accountService.getAccountForm(accountOptional.get(), accountType);
    return ResponseEntity.ok(accountForm);
  }

  @PostMapping("/api/account/login")
  @CrossOrigin
  public ResponseEntity<?> accountLogin(@RequestBody AccountLoginForm accountPassword) {
    if(accountService.accountLogin(accountPassword.getAccount_number(), accountPassword.getPassword_hash())) {
      Optional<Account> account = accountService.getAccountByAccountNumber(accountPassword.getAccount_number());
      AccountType accountType = accountService.getAccountTypeByAccountId(account.get()
          .getAccount_id());
      return ResponseEntity.ok(accountService.getAccountForm(account.get(), accountType));
    }
    return ResponseEntity.badRequest().body("Login failed");
  }



}
