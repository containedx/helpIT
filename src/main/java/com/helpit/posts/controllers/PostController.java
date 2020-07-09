package com.helpit.posts.controllers;

import com.helpit.events.Event;
import com.helpit.model.Foundation;
import com.helpit.model.Types;
import com.helpit.model.User;
import com.helpit.posts.model.Post;
import com.helpit.posts.repositories.PostRepository;
import com.helpit.repositories.FoundationRepository;
import com.helpit.repositories.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Optional;
import java.util.*;
import java.util.stream.Collectors;

@Controller
public class PostController {
    private final PostRepository postRepository;
    private final FoundationRepository foundationRepository;
    private final UserRepository userRepository;

    public PostController(PostRepository postRepository, FoundationRepository foundationRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.foundationRepository = foundationRepository;
        this.userRepository = userRepository;
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

//    @RequestMapping("article/delete/{id}")
//    public String deletePost(@Valid @ModelAttribute("post") Post post) {
//        delete(post.getId());
//        return "redirect:/";
//    }

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

    public void delete(Integer id){
        postRepository.deleteById(id);
    }

    @RequestMapping({"/article/{id}/delete"})
    public String deletePost(@PathVariable String id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = auth.getName();
        User user = userRepository.findByEmail(currentUserName);

        try {
            postRepository.deleteById(Integer.valueOf(id));
        } catch (Exception e) {
            e.printStackTrace();
        }


        if(user.getFoundation() != null) {
            return "redirect:/foundation";
        }
        else if(user.getVolunteer() != null) {
            return "redirect:/volunteer";
        }
        else return "index";
    }

    @RequestMapping({"/article/filter"})
    public String filterArticles(Model model,
                                 @RequestParam String category,
                                 @RequestParam String foundation) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = auth.getName();
        User user = userRepository.findByEmail(currentUserName);

        model.addAttribute("volunteer", user.getVolunteer());

        //filtracja
        if(category.equals("all") && foundation.equals("all")) {
            //wrzucam wszystkie
            List<Post> listOfArticles = new ArrayList<>(user.getVolunteer().getPosts());
            listOfArticles.sort(Comparator.comparing(Post::getCreateTime).reversed());
            model.addAttribute("articles", listOfArticles);
        }
        else if (!category.equals("all") && foundation.equals("all")) {
            List<Post> filered = user.getVolunteer().getPosts().stream().filter(post -> post.getCategory().equals(Types.valueOf(category))).sorted(Comparator.comparing(Post::getCreateTime).reversed()).collect(Collectors.toList());
            model.addAttribute("articles", filered);
        }
        else if (category.equals("all") && !foundation.equals("all")) {
            List<Post> filered = user.getVolunteer().getPosts().stream().filter(post -> post.getFoundation().getId().equals(Integer.valueOf(foundation))).sorted(Comparator.comparing(Post::getCreateTime).reversed()).collect(Collectors.toList());
            model.addAttribute("articles", filered);
        }
        else {
            List<Post> filered = user.getVolunteer().getPosts().stream().filter(post -> post.getCategory().equals(Types.valueOf(category)) && post.getFoundation().getId().equals(Integer.valueOf(foundation))).sorted(Comparator.comparing(Post::getCreateTime).reversed()).collect(Collectors.toList());
            model.addAttribute("articles", filered);
        }

        model.addAttribute("foundations", foundationRepository.findAll());
        return "volunteer/show";
    }



    @RequestMapping({"/foundation/article/filter"})
    public String filterArticlesBelongingToFundation(Model model,
                                 @RequestParam String category) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = auth.getName();
        User user = userRepository.findByEmail(currentUserName);

        model.addAttribute("foundation", user.getFoundation());

        //filtracja
        if(category.equals("all")) {
            //wrzucam wszystkie
            List<Post> listOfArticles = new ArrayList<>(user.getFoundation().getPost());
            listOfArticles.sort(Comparator.comparing(Post::getCreateTime).reversed());
            model.addAttribute("articles", listOfArticles);
        }
        else {
            List<Post> filtered = user.getFoundation().getPost().stream().filter(post -> post.getCategory().equals(Types.valueOf(category))).sorted(Comparator.comparing(Post::getCreateTime).reversed()).collect(Collectors.toList());
            model.addAttribute("articles", filtered);
        }

        return "foundation/show";
    }


}
