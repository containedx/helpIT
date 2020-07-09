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


}

