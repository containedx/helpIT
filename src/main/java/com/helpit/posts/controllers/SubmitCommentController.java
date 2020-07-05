package com.helpit.posts.controllers;

import com.helpit.model.User;
import com.helpit.posts.model.Comment;
import com.helpit.model.Foundation;
import com.helpit.model.Volunteer;
import com.helpit.posts.model.Post;
import com.helpit.posts.repositories.CommentRepository;
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
public class SubmitCommentController
{
    private CommentRepository commentRepository;
    private UserRepository userRepository;
    private FoundationRepository foundationRepository;

    public SubmitCommentController(CommentRepository commentRepository, UserRepository userRepository, FoundationRepository foundationRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.foundationRepository = foundationRepository;
    }

    @RequestMapping("/charity/{id}/submitopinion")
    public String submitOpinion(@PathVariable String id,
                                @RequestParam  String editordata,
                                @RequestParam  String rating)
    {
        try{
            Comment comment = new Comment();
            comment.setContent(editordata);
            comment.setRate(Integer.valueOf(rating));
            commentRepository.save(comment);

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String currentUserName = auth.getName();
            User user = userRepository.findByEmail(currentUserName);
            user.getVolunteer().getComments().add(comment);
            userRepository.save(user);

            Optional<Foundation> f = foundationRepository.findById(Integer.valueOf(id));
            if (f.isPresent()) {
                comment.setVolunteer(user.getVolunteer());
                comment.setFoundation(f.get());
                f.get().getComment().add(comment);
                foundationRepository.save(f.get());
                commentRepository.save(comment);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/charity/" + id + "/opinion";
    }
}