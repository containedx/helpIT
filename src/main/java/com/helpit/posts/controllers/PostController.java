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
    private final PostRepository postRepository;
    private final FoundationRepository foundationRepository;

    public PostController(PostRepository postRepository, FoundationRepository foundationRepository) {
        this.postRepository = postRepository;
        this.foundationRepository = foundationRepository;
    }


    @RequestMapping({"/article/show"})
    public String getPostShow()
    {
        return "/article/show_default";
    }

    @RequestMapping({"/article/{id}/show"})
    public String getPostShowById(@PathVariable String id, Model model)
    {
        Optional<Post> post = postRepository.findById(Integer.valueOf(id));
        if (post.isPresent()) {
            model.addAttribute("article", post.get());
        }
        else {
            throw new RuntimeException("Sth went wrong");
        }
        return "/article/show";
    }

    @RequestMapping({"/article/edit"})
    public String getPostEdit()
    {
        return "/article/edit";
    }

    @RequestMapping({"/article/adding"})
    public String getPostAdd(Model model)
    {
        model.addAttribute("foundations", foundationRepository.findAll());
        return "/article/add";
    }

}
