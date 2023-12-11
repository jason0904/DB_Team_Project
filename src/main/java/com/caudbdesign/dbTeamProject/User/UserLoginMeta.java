package com.caudbdesign.dbTeamProject.User;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Getter
@Setter
public class UserLoginMeta {

  private int uid;
  private String login_id;
  private int login_attempt;

}
