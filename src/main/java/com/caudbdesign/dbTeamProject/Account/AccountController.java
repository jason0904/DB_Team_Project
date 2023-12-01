package com.caudbdesign.dbTeamProject.Account;


import java.util.Optional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@Getter
@RequiredArgsConstructor
public class AccountController {

  private final AccountService accountService;

  @PostMapping("/api/account")
  public ResponseEntity<?> getAccountInfo(@RequestBody Account account) {
    Optional<Account> accountInfo = accountService.getAccountByAccountNumber(
        account.getAccount_number());
    if (accountInfo.isPresent()) {
      return ResponseEntity.ok(accountInfo.get());
    } else {
      return ResponseEntity.badRequest().body("Invalid Account Number");
    }
  }

}
