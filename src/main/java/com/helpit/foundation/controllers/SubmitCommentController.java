package com.helpit.foundation.controllers;

import com.helpit.foundation.model.Comment;
import com.helpit.foundation.model.Foundation;
import com.helpit.foundation.model.User;
import com.helpit.foundation.repositories.CommentRepository;
import com.helpit.foundation.repositories.FoundationRepository;
import com.helpit.foundation.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SubmitCommentController
{
    private CommentRepository comment_repository;
    private UserRepository user_repository;
    private FoundationRepository foundation_repository;

    public SubmitCommentController(CommentRepository comment_repository, UserRepository user_repository, FoundationRepository foundation_repository)
    {
        this.comment_repository = comment_repository;
        this.user_repository = user_repository;
        this.foundation_repository = foundation_repository;
    }

    @RequestMapping("/list")
    public String getComments(Model model,
                              @RequestParam  String login,
                              @RequestParam  String foundation,
                              @RequestParam  String content)
    {
        Comment c = new Comment();
        c.setContent(content);
        comment_repository.save(c);

        Foundation f = new Foundation();
        f.setName(foundation);
        foundation_repository.save(f);

        User u = new User();
        u.setLogin(login);
        user_repository.save(u);

        c.setUser(u);
        c.setFoundation(f);

        u.getComments().add(c);
        f.getComment().add(c);

        foundation_repository.save(f);
        user_repository.save(u);
        comment_repository.save(c);

        model.addAttribute("comments", comment_repository.findAll());
        return "/add_comment/list";
    }
}
