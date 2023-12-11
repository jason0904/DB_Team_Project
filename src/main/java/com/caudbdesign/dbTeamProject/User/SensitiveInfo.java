package com.caudbdesign.dbTeamProject.User;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@RequiredArgsConstructor
public class SensitiveInfo {

    private Integer uid;
    private String email;
    private String phone;
    private Integer age;
    private String sex;

}
