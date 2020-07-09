package com.helpit.posts.controllers;

import com.helpit.model.Foundation;
import com.helpit.model.User;
import com.helpit.posts.model.Comment;
import com.helpit.posts.model.Post;
import com.helpit.posts.repositories.PostRepository;
import com.helpit.repositories.FoundationRepository;
import com.helpit.repositories.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Controller
public class FoundationController {
    private final FoundationRepository foundationRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public FoundationController(FoundationRepository foundationRepository, UserRepository userRepository, PostRepository postRepository) {
        this.foundationRepository = foundationRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }


    @RequestMapping({"/foundation/list", "/foundation/list.html"})
    public String getFoundations(Model model) {

        model.addAttribute("foundations", foundationRepository.findAll());
        return "/foundation/list";
    }

    @RequestMapping({"/foundation/{id}/show"})
    public String getFoundationSite(@PathVariable String id, Model model) {
        Optional<Foundation> foundation = foundationRepository.findById(Integer.valueOf(id));

        if(foundation.isPresent()){
            model.addAttribute("foundation", foundation.get());
        }
        else {
            throw new RuntimeException("Cannot display foundation, because it is not present in the database");
        }
        return "/foundation/show";
    }


    @RequestMapping({"/charity/show"})
    public String getCharityShow()
    {
        return "/charity/show_default";
    }


    @RequestMapping({"/charity/{id}/show"})
    public String getCharityShow(@PathVariable String id, Model model)
    {
        Optional<Foundation> foundation = foundationRepository.findById(Integer.valueOf(id));

        if(foundation.isPresent()){
            model.addAttribute("foundation", foundation.get());

            //sortowanie w kolejnosci od najnowszych
            List<Post> listOfArticles = new ArrayList<>(foundation.get().getPost());
            listOfArticles.sort(Comparator.comparing(Post::getCreateTime).reversed());
            model.addAttribute("articles", listOfArticles);
        }
        else {
            throw new RuntimeException("Cannot display foundation, because it is not present in the database");
        }

        return "/charity/show";
    }

    @RequestMapping({"/charity/events"})
    public String getCharityEvents()
    {
        return "/charity/events";
    }


    @RequestMapping({"/charity/{id}/opinion"})
    public String getCharityOpinions(@PathVariable String id,
                                     Model model)
    {
        Optional<Foundation> foundation = foundationRepository.findById(Integer.valueOf(id));
        if(foundation.isPresent()){
            List<Comment> opinions = new ArrayList<>(foundation.get().getComment());
            opinions.sort(Comparator.comparing(Comment::getCreateTime).reversed());
            model.addAttribute("opinions", opinions);

            model.addAttribute("foundation", foundation.get());

            double sumOfRates = foundation.get().getComment().stream().map(comment -> comment.getRate()).mapToInt(Integer::intValue).sum();
            double rating = sumOfRates / (double)foundation.get().getComment().size(); //
            model.addAttribute("rating", rating);
        }
        else {
            throw new RuntimeException("Cannot display foundation, because it is not present in the database");
        }
        return "/charity/opinions";
    }


    @RequestMapping({"/charity/edit"})
    public String getCharityEdit()
    {
        return "/registrations/edit_charity";
    }

    @RequestMapping({"/charities"})
    public String getFoundList(Model model)
    {
        model.addAttribute("foundations", foundationRepository.findAll());
        return "/charity/list";
    }

    @RequestMapping({"/foundation"})
    public String getFoundation(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = auth.getName();
        User user = userRepository.findByEmail(currentUserName);

        model.addAttribute("foundation", user.getFoundation());

        List<Post> listOfArticles = new ArrayList<>(user.getFoundation().getPost());
        listOfArticles.sort(Comparator.comparing(Post::getCreateTime).reversed());
        model.addAttribute("articles", listOfArticles);

        return "/foundation/show";
    }

}

