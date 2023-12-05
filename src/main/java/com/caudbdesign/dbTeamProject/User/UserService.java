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
    if(login.isPresent()) {
      if(login.get().getLogin_attempt() >= 10) {
        updateStatusByUID(uid);
        return false;
      }
      if (login.get().getPassword_hash().equals(pw)) {
        updateAttemptByUID(uid, 0);
        return true;
      }
    }

    updateAttemptByUID(uid, login.get().getLogin_attempt() + 1);
    return false;
  }

  public User getUserbyUID(int uid) {
    Optional<User> user = userRepository.findUserByUID(uid);
    return user.orElse(null);
  }

  public void updateStatusByUID(int uid) {
    userRepository.updateStatusByUID(uid);
  }

  public void updateAttemptByUID(int uid, int attempt) {
    userRepository.updateAttemptByUID(uid, attempt);
  }

  public Integer getUIDbyID(String id) {
    return userRepository.findByID(id);
  }
}
