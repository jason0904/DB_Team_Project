package com.caudbdesign.dbTeamProject.User;

import java.lang.reflect.Member;
import java.util.Optional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Getter
public class UserService {

  private final UserRepository userRepository;
  public boolean validateLogin(String id, String pw) {
    Integer uid = userRepository.findByID(id);
    if (uid == null) {
      return false;
    }
    Optional<Login> login = userRepository.findByUID(uid);
    return login.map(value -> value.getPassword_hash().equals(pw)).orElse(false);
  }

  public User getUserbyUID(int uid) {
    Optional<User> user = userRepository.findUserByUID(uid);
    return user.orElse(null);
  }

  public Integer getUIDbyID(String id) {
    return userRepository.findByID(id);
  }

}
