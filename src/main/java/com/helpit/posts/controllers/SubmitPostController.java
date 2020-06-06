package com.helpit.posts.controllers;


import com.helpit.posts.pcmodel.Foundation;
import com.helpit.posts.pcmodel.Post;
import com.helpit.posts.pcmodel.Volunteer;
import com.helpit.posts.repositories.FoundationRepository;
import com.helpit.posts.repositories.PostRepository;
import com.helpit.posts.repositories.VolunteerRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class SubmitPostController {
    private PostRepository postRepository;
    private VolunteerRepository volunteerRepository;
    private FoundationRepository foundationRepository;

    public SubmitPostController(PostRepository postRepository, VolunteerRepository volunteerRepository, FoundationRepository foundationRepository) {
        this.postRepository = postRepository;
        this.volunteerRepository = volunteerRepository;
        this.foundationRepository = foundationRepository;
    }

    @RequestMapping("/add_post/submit")
    public String getComments(Model model,
                              @RequestParam String login,
                              @RequestParam  String foundation,
                              @RequestParam  String content)
    {
        Post c = new Post();
        c.setContent(content);
        postRepository.save(c);

        Foundation f = new Foundation();
        f.setName(foundation);
        foundationRepository.save(f);

        Volunteer u = new Volunteer();
        u.setLogin(login);
        volunteerRepository.save(u);

        c.setVolunteer(u);
        c.setFoundation(f);

        u.getPosts().add(c);
        f.getPost().add(c);

        foundationRepository.save(f);
        volunteerRepository.save(u);
        postRepository.save(c);

        model.addAttribute("comments", postRepository.findAll());
        return "redirect:/add_post/list";
    }


    @RequestMapping("/add_post/{id}/submit_2_foundation")
    public String addPost(@PathVariable String id,
                          @RequestParam String login,
                          @RequestParam  String content,
                          Model model)
    {
        Post c = new Post();
        c.setContent(content);
        postRepository.save(c);


        Volunteer u = new Volunteer();
        u.setLogin(login);
        volunteerRepository.save(u);

        Optional<Foundation> f = foundationRepository.findById(Long.valueOf(id));
        if (f.isPresent()) {

            c.setVolunteer(u);
            c.setFoundation(f.get());
            u.getPosts().add(c);
            f.get().getPost().add(c);
            foundationRepository.save(f.get());
        }



        volunteerRepository.save(u);
        postRepository.save(c);

        model.addAttribute("comments", postRepository.findAll());
        return "redirect:/foundation/" + id + "/show";
    }
}

