package com.helpit.posts.controllers;

import com.helpit.model.User;
import com.helpit.posts.model.CommentUnderPost;
import com.helpit.posts.model.Post;
import com.helpit.posts.repositories.CommentUnderPostRepository;
import com.helpit.posts.repositories.PostRepository;
import com.helpit.repositories.UserRepository;
import com.helpit.repositories.VolunteerRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class SubmitCommentUnderPostController {
    private final CommentUnderPostRepository commentUnderPostRepository;
    private final VolunteerRepository volunteerRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public SubmitCommentUnderPostController(CommentUnderPostRepository commentUnderPostRepository, VolunteerRepository volunteerRepository, PostRepository postRepository, UserRepository userRepository) {
        this.commentUnderPostRepository = commentUnderPostRepository;
        this.volunteerRepository = volunteerRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @RequestMapping({"/article/{id}/add_comment"})
    public String addArticleToFoundation(@PathVariable String id,
                                         @RequestParam String editordata)
    {
        CommentUnderPost c = new CommentUnderPost();
        c.setContent(editordata);
        commentUnderPostRepository.save(c);


        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = auth.getName();
        User user = userRepository.findByEmail(currentUserName);
        user.getVolunteer().getCommentsUnderPost().add(c);
        userRepository.save(user);


        Optional<Post> f = postRepository.findById(Integer.valueOf(id));
        if (f.isPresent()) {
            c.setVolunteer(user.getVolunteer());
            c.setPost(f.get());
            f.get().getCommentsUnderPost().add(c);
            postRepository.save(f.get());
        }


        return "redirect:/article/" + id + "/show";
    }
}
