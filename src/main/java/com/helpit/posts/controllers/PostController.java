package com.helpit.posts.controllers;

import com.helpit.events.Event;
import com.helpit.model.Foundation;
import com.helpit.posts.model.Post;
import com.helpit.posts.repositories.PostRepository;
import com.helpit.repositories.FoundationRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class PostController {
    private final PostRepository postRepository;
    private final FoundationRepository foundationRepository;

    public PostController(PostRepository postRepository, FoundationRepository foundationRepository) {
        this.postRepository = postRepository;
        this.foundationRepository = foundationRepository;
    }


    @RequestMapping({"/add_post/add"})
    public String getAddComment()
    {
        return "/add_post/add";
    }

    @RequestMapping({"/add_post/{id}/add_2_foundation"})
    public String AddPost2Foundation(@PathVariable String id, Model model)
    {
        Optional<Foundation> foundation = foundationRepository.findById(Integer.valueOf(id));
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
        model.addAttribute("posts", postRepository.findAll());
        return "/add_post/list";
    }

    @RequestMapping({"/add_post/{id}/display_post"})
    public String getListComment(@PathVariable String id, Model model)
    {

        Optional<Post> post = postRepository.findById(Integer.valueOf(id));
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

    @RequestMapping("article/delete/{id}")
    public String deletePost(@Valid @ModelAttribute("post") Post post) {
        delete(post.getId());
        return "redirect:/";
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


    public void delete(Integer id){
        postRepository.deleteById(id);
    }


}
