package com.helpit.posts.controllers;

import com.helpit.model.Address;
import com.helpit.model.User;
import com.helpit.model.Volunteer;
import com.helpit.posts.model.Post;
import com.helpit.posts.services.ImageService;
import com.helpit.repositories.FoundationRepository;
import com.helpit.repositories.UserRepository;
import com.helpit.repositories.VolunteerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Controller
public class VolunteerController {

    private final UserRepository userRepository;
    private final FoundationRepository foundationRepository;
    private final VolunteerRepository volunteerRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private ImageService imageService;

    public VolunteerController(UserRepository userRepository, FoundationRepository foundationRepository, VolunteerRepository volunteerRepository) {
        this.userRepository = userRepository;
        this.foundationRepository = foundationRepository;
        this.volunteerRepository = volunteerRepository;
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
    public String getVolunteerEdit(Model model)
    {
        User user = new User();
        user.setVolunteer(new Volunteer());
        user.setAddress(new Address());
        model.addAttribute("user", user);
        return "/volunteer/edit";
    }

    @RequestMapping({"/volunteer/edit/submit"})
    public String submitVolunteerEdition(Model model,
                                         @ModelAttribute User user,
                                         @RequestParam String oldpassword) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = auth.getName();
        User loggedUser = userRepository.findByEmail(currentUserName);

        if( bCryptPasswordEncoder.matches(oldpassword,user.getPassword()) )
        {
            model.addAttribute("error", "Wrong old password!!");
            return "oops";
        }

        loggedUser.setEmail(user.getEmail());
        loggedUser.setUsername(user.getUsername());
        loggedUser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        loggedUser.getVolunteer().setName(user.getVolunteer().getName());
        loggedUser.getVolunteer().setSurname(user.getVolunteer().getSurname());
        loggedUser.setAddress(user.getAddress());

        userRepository.save(loggedUser);
        volunteerRepository.save(loggedUser.getVolunteer());

        //przydaloby sie miec widok gdzie wypisywalibyśmy te ustawienia

        return "redirect:/volunteer";
    }
}
