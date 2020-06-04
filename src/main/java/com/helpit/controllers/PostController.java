package com.helpit.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PostController {

    @RequestMapping({"/article/show"})
    public String getPostShow()
    {
        return "/article/show";
    }

    @RequestMapping({"/article/edit"})
    public String getPostEdit()
    {
        return "/article/edit";
    }

    @RequestMapping({"/article/add"})
    public String getPostAdd()
    {
        return "/article/add";
    }
}
