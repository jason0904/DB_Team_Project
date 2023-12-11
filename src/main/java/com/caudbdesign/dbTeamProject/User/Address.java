package com.caudbdesign.dbTeamProject.User;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@RequiredArgsConstructor
public class Address {

    private Integer address_id;
    private Integer uid;
    private String address;
    private int postal_code;
    private String country;


}
