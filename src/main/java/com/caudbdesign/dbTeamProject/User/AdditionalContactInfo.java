package com.caudbdesign.dbTeamProject.User;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@RequiredArgsConstructor
public class AdditionalContactInfo {

  private int contatct_info_id;
  private int uid;
  private String ContactType;
  private String ContactValue;
}
