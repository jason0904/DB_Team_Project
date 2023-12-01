package com.caudbdesign.dbTeamProject.User;

import java.util.ArrayList;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@AllArgsConstructor
public class UserAccountForm {

  private String username;
  private ArrayList<String> accountNumbers = new ArrayList<>();



}
