package com.caudbdesign.dbTeamProject.User;

import com.caudbdesign.dbTeamProject.Account.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
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
  public ResponseEntity<?> login(@RequestBody UserLoginForm userLoginForm) {
    if(userLoginForm.getID() == null || userLoginForm.getPassword_hash() == null) {
      return ResponseEntity.badRequest().body("ID and password are required");
    }
    if(userService.validateLogin(userLoginForm.getID(), userLoginForm.getPassword_hash())) {
      Integer uid = userService.getUIDbyID(userLoginForm.getID());
      User user = userService.getUserbyUID(uid);
      UserAccountForm userAccountForm = new UserAccountForm(user.getUid(), user.getUsername(), accountService.getAccountNumbersByUID(uid), accountService.getAccountTypesByUID(uid));
      return ResponseEntity.ok(userAccountForm);
    } else {
      return ResponseEntity.badRequest().body("Invalid ID, password or, your account is inactive.");
    }
  }


  @PostMapping("/api/info")
  @CrossOrigin
  public ResponseEntity<?> info(@RequestBody int uid) {
    UserinformationForm userinformationForm = userService.getUserInformation(uid);
    return ResponseEntity.ok(userinformationForm);
  }

  @PostMapping("/api/info/modify")
  @CrossOrigin
  public ResponseEntity<?> modify(@RequestBody UserinformationForm userinformationForm) {
    userService.updateUserInformation(userinformationForm);
    return ResponseEntity.ok("success");
  }


}
