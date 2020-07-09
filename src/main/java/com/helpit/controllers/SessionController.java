package com.helpit.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class SessionController {

    @RequestMapping({"/login/charity"})
    public String getLoginCharity()
    {
        return "/sessions/new_charity";
    }

    @RequestMapping({"/login/volunteer"})
    public String getLoginVolunteer()
    {
        return "/sessions/new_volunteer";
    }

    @RequestMapping({"/signup/charity"})
    public String getSignupCharity()
    {
        return "/registrations/new_charity";
    }

    @RequestMapping({"/signup/volunteer"})
    public String getSignupVolunteer()
    {
        return "/registrations/new_volunteer";
    }

}
