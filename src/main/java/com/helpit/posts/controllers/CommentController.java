package com.helpit.posts.controllers;

import com.helpit.posts.repositories.CommentRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CommentController
{
    private final CommentRepository comment_repository;

    public CommentController(CommentRepository comment_repository)
    {
        this.comment_repository = comment_repository;
    }

    @RequestMapping({"/add_comment/add"})
    public String getAddComment()
    {
        return "/add_comment/add";
    }

    @RequestMapping({"/add_comment/list"})
    public String getListComment(Model model)
    {
        model.addAttribute("comments", comment_repository.findAll());
        return "/add_comment/list";
    }

}