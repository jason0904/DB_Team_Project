package com.caudbdesign.dbTeamProject.User;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;


@Getter
@Setter
@Component
public class Login {

  private Integer Uid;
  @JsonProperty("id")
  private String ID;
  private String password_hash;
  private Integer login_attempt;
  private String security_question;
  private String security_answer_hash;

  public Login(String ID, String password_hash) {

    this.ID = ID;
    this.password_hash = password_hash;

  }

  public Login() {

  }

}
