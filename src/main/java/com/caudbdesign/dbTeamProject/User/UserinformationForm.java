package com.caudbdesign.dbTeamProject.User;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@RequiredArgsConstructor
public class UserinformationForm {

  //SensitiveInfo와 Address, AdditionalContactInfo를 합친 클래스
  private int uid;
  private String ContactType;
  private String ContactValue;
  private String address;
  private int postal_code;
  private String country;
  private String email;
  private String phone;
  private Integer age;
  private String sex;


}
