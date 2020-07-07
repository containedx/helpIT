package com.helpit.posts.controllers;

import com.helpit.model.User;
import com.helpit.posts.model.Post;
import com.helpit.repositories.FoundationRepository;
import com.helpit.repositories.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Controller
public class VolunteerController {

    private final UserRepository userRepository;
    private final FoundationRepository foundationRepository;

    public VolunteerController(UserRepository userRepository, FoundationRepository foundationRepository) {
        this.userRepository = userRepository;
        this.foundationRepository = foundationRepository;
    }


    @RequestMapping({"/volunteer"})
    public String getVolunteer(Model model)
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = auth.getName();
        User user = userRepository.findByEmail(currentUserName);

        model.addAttribute("volunteer", user.getVolunteer());

        List<Post> listOfArticles = new ArrayList<>(user.getVolunteer().getPosts());
        listOfArticles.sort(Comparator.comparing(Post::getCreateTime).reversed());
        model.addAttribute("articles", listOfArticles);

        model.addAttribute("foundations", foundationRepository.findAll());

        return "/volunteer/show";
    }

    @RequestMapping({"/volunteer/edit"})
    public String getVolunteerEdit()
    {
        return "/volunteer/edit";
    }
}
