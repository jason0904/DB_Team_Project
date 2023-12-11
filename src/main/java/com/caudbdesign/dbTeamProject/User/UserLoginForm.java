package com.caudbdesign.dbTeamProject.User;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Getter
@Setter
public class UserLoginForm {

  @JsonProperty("id")
  private String ID;
  private String password_hash;

}
