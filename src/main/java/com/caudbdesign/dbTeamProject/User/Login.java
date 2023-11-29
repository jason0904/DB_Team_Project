package com.caudbdesign.dbTeamProject.User;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Bean;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Component;


@Getter
@Setter
@Component
@RequiredArgsConstructor
public class Login {

  private Integer uID;
  private String ID;
  private String password_hash;

  public Login(Integer uID, String id, String pw) {
    this.uID = uID;
    this.ID = id;
    this.password_hash = pw;
  }




}
