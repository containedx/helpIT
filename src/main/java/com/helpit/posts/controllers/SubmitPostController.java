package com.helpit.posts.controllers;


import com.helpit.model.Foundation;
import com.helpit.model.Types;
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

    @RequestMapping({"/charity/{id}/add_article"})
    public String addArticleToFoundation(@PathVariable String id,
                                         @RequestParam String title,
                                         @RequestParam String editordata,
                                         @RequestParam String category)
    {
        Post c = new Post();
        c.setContent(editordata);
        c.setTitle(title);
        c.setCategory(Types.valueOf(category));
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

        return "redirect:/charity/" + id + "/show";
    }

    @RequestMapping("/article/submit")
    public String addArticleToSelectedFoundation( @RequestParam String title,
                                                  @RequestParam String selected,
                                                  @RequestParam String editordata,
                                                  @RequestParam String category) {
        Post post = new Post();
        post.setContent(editordata);
        post.setTitle(title);
        post.setCategory(Types.valueOf(category));
        postRepository.save(post);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = auth.getName();
        User user = userRepository.findByEmail(currentUserName);
        user.getVolunteer().getPosts().add(post);
        userRepository.save(user);

        Optional<Foundation> f = foundationRepository.findById(Integer.valueOf(selected));
        if (f.isPresent()) {
            post.setVolunteer(user.getVolunteer());
            post.setFoundation(f.get());
            f.get().getPost().add(post);
            foundationRepository.save(f.get());
        }
        else
        {
            throw new RuntimeException("Selected foundation not found");
        }

        return "redirect:/charity/" + selected + "/show";
    }
}

