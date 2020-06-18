package com.helpit.posts.controllers;

import com.helpit.model.Foundation;
import com.helpit.posts.model.Post;
import com.helpit.posts.repositories.PostRepository;
import com.helpit.repositories.FoundationRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
public class PostController {
    private final PostRepository post_repository;
    private final FoundationRepository foundation_repository;

    public PostController(PostRepository post_repository, FoundationRepository foundation_repository) {
        this.post_repository = post_repository;
        this.foundation_repository = foundation_repository;
    }

    @RequestMapping({"/add_post/add"})
    public String getAddComment()
    {
        return "/add_post/add";
    }

    @RequestMapping({"/add_post/{id}/add_2_foundation"})
    public String AddPost2Foundation(@PathVariable String id, Model model)
    {
        Optional<Foundation> foundation = foundation_repository.findById(Integer.valueOf(id));
        if (foundation.isPresent()) {
            model.addAttribute("foundation", foundation.get());
        }
        else {
            throw new RuntimeException("Sth went wrong");
        }
        return "/add_post/add_2_foundation";
    }

    @RequestMapping({"/add_post/list"})
    public String getListComment(Model model)
    {
        System.out.println("kurwa 1");
        model.addAttribute("posts", post_repository.findAll());
        System.out.println("kurwa 2");
        return "/add_post/list";
    }

    @RequestMapping({"/add_post/{id}/display_post"})
    public String getListComment(@PathVariable String id, Model model)
    {

        Optional<Post> post = post_repository.findById(Integer.valueOf(id));
        if (post.isPresent()) {
            model.addAttribute("post", post.get());
        }
        else {
            throw new RuntimeException("Sth went wrong");
        }



        return "/add_post/display_post";
    }

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
