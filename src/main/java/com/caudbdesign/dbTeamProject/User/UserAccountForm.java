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
@RequiredArgsConstructor
public class UserAccountForm {

  private Integer uid;
  private String username;
  private ArrayList<String> accountNumbers = new ArrayList<>();
  private ArrayList<String> accountTypes = new ArrayList<>();



}
