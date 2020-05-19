package com.helpit.foundation.controllers;

import com.helpit.foundation.repositories.CommentRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CommentController
{

    public CommentController()
    {
    }

    @RequestMapping("/")
    public String getAddComment(Model model)
    {
        return "/add_comment/add";
    }
}
