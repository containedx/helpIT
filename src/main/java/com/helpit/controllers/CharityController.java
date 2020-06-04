package com.helpit.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CharityController {

    @RequestMapping({"/charity/show"})
    public String getCharityShow()
    {
        return "/charity/show";
    }

    @RequestMapping({"/charity/events"})
    public String getCharityEvents()
    {
        return "/charity/events";
    }

    @RequestMapping({"/charity/sponsors"})
    public String getCharitySponsors()
    {
        return "/charity/sponsors";
    }

    @RequestMapping({"/charity/edit"})
    public String getCharityEdit()
    {
        return "/registrations/edit_charity";
    }
}
