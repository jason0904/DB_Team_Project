package com.caudbdesign.dbTeamProject.User;

import java.sql.Timestamp;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;


@Getter
@Setter
@Component
@RequiredArgsConstructor
public class UserLoginPassword {

  private Integer uid;
  private String password_hash;
  private Timestamp updated_at;
}
