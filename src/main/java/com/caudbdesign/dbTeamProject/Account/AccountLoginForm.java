package com.caudbdesign.dbTeamProject.Account;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Getter
@Setter
public class AccountLoginForm {

    private String account_number;
    private String password_hash;

}
