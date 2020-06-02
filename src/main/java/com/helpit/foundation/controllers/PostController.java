package com.helpit.foundation.controllers;

import com.helpit.foundation.model.Foundation;
import com.helpit.foundation.model.Post;
import com.helpit.foundation.repositories.FoundationRepository;
import com.helpit.foundation.repositories.PostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.websocket.server.PathParam;
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
        Optional<Foundation> foundation = foundation_repository.findById(Long.valueOf(id));
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
        model.addAttribute("posts", post_repository.findAll());
        return "/add_post/list";
    }

    @RequestMapping({"/add_post/{id}/display_post"})
    public String getListComment(@PathVariable String id, Model model)
    {

        Optional<Post> post = post_repository.findById(Long.valueOf(id));
        if (post.isPresent()) {
            model.addAttribute("post", post.get());
        }
        else {
            throw new RuntimeException("Sth went wrong");
        }



        return "/add_post/display_post";
    }
}
