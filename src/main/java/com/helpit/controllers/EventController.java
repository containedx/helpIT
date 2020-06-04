package com.helpit.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class EventController {

    @RequestMapping({"/event/show"})
    public String getPostShow()
    {
        return "/event/show";
    }

    @RequestMapping({"/event/edit"})
    public String getPostEdit()
    {
        return "/event/edit";
    }
}
