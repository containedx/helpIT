package com.helpit.foundation.controllers;

import com.helpit.foundation.repositories.PostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PostController {
    private final PostRepository post_repository;

    public PostController(PostRepository post_repository) {
        this.post_repository = post_repository;
    }

    @RequestMapping({"/add_post/add"})
    public String getAddComment()
    {
        return "/add_post/add";
    }

    @RequestMapping({"/add_post/list"})
    public String getListComment(Model model)
    {
        model.addAttribute("posts", post_repository.findAll());
        return "/add_post/list";
    }
}
