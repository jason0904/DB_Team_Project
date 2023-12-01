package com.caudbdesign.dbTeamProject.User;

import com.caudbdesign.dbTeamProject.Account.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;
  private final AccountService accountService;

  @PostMapping("/api/login")
  @CrossOrigin
  public ResponseEntity<?> authenticateUser(@RequestBody Login login) {
    if(userService.validateLogin(login.getID(), login.getPassword_hash())) {
      Integer uid = userService.getUIDbyID(login.getID());
      if(uid == null) {
        return ResponseEntity.badRequest().body("Invalid ID");
      }
      User user = userService.getUserbyUID(uid);
      if(user.getAccount_status().equals("inactive")) {
        return ResponseEntity.badRequest().body("Inactive Account");
      }
      UserAccountForm userAccountForm = new UserAccountForm(user.getUID(), user.getUsername(), accountService.getAccountNumbersByUID(uid));
      return ResponseEntity.ok(userAccountForm);
    } else {
      return ResponseEntity.badRequest().body("Invalid Password");
    }
  }


}
