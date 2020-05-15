package com.helpit.security;

import lombok.Data;

@Data
public class VolunteerRegisterForm {
    private String username;
    private String password;
    private String name;
    private String surname;
    private String city;
    private String zip;
    private String phone;

}
