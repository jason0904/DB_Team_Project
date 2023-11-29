package com.caudbdesign.dbTeamProject.User;

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

  @PostMapping("/api/login")
  @CrossOrigin(origins = "http://192.168.5.111:31991")
  public ResponseEntity<?> authenticateUser(@RequestBody Login login) {
    if(userService.validateLogin(login.getID(), login.getPassword_hash())) {
      Integer uid = userService.getUIDbyID(login.getID());
      if(uid == null) {
        return ResponseEntity.badRequest().build();
      }
      User user = userService.getUserbyUID(uid);
      return ResponseEntity.ok(user);
    } else {
      return ResponseEntity.badRequest().build();
    }
  }
}
