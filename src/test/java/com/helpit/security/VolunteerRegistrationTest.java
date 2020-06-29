package com.helpit.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

class VolunteerRegistrationTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    public void setup(){
        mockMvc= MockMvcBuilders.webAppContextSetup(context).apply(SecurityMockMvcConfigurers.springSecurity()).build();
    }

    public void testSaveVolunteer(){

    }
}