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
public class User {

  private Integer Uid;
  private String username;
  private String usertype;
  private String account_status;
  private Timestamp created_at;
  private Timestamp updated_at;


}
