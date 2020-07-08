package com.helpit.posts.controllers;


import com.helpit.model.Foundation;
import com.helpit.model.User;
import com.helpit.posts.model.Post;
import com.helpit.model.Volunteer;
import com.helpit.posts.repositories.PostRepository;
import com.helpit.repositories.FoundationRepository;
import com.helpit.repositories.UserRepository;
import com.helpit.repositories.VolunteerRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class SubmitPostController {
    private final PostRepository postRepository;
    private final VolunteerRepository volunteerRepository;
    private final FoundationRepository foundationRepository;
    private final UserRepository userRepository;

    public SubmitPostController(PostRepository postRepository, VolunteerRepository volunteerRepository, FoundationRepository foundationRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.volunteerRepository = volunteerRepository;
        this.foundationRepository = foundationRepository;
        this.userRepository = userRepository;
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
        u.setSurname(login);
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
        u.setSurname(login);
        volunteerRepository.save(u);

        Optional<Foundation> f = foundationRepository.findById(Integer.valueOf(id));
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

    @RequestMapping({"/foundation/{id}/add_article"})

    public String addArticleToFoundation(@PathVariable String id,
                                         @RequestParam String title,
                                         @RequestParam String editordata)
    {
        Post c = new Post();
        c.setContent(editordata);
        c.setTitle(title);
        postRepository.save(c);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = auth.getName();
        User user = userRepository.findByEmail(currentUserName);
        user.getVolunteer().getPosts().add(c);
        userRepository.save(user);

        Optional<Foundation> f = foundationRepository.findById(Integer.valueOf(id));
        if (f.isPresent()) {
            c.setVolunteer(user.getVolunteer());
            c.setFoundation(f.get());
            f.get().getPost().add(c);
            foundationRepository.save(f.get());
        }

        return "redirect:/foundation/" + id + "/show";

    }
}

