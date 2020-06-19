package com.helpit.posts.controllers;

import com.helpit.posts.model.Comment;
import com.helpit.model.Foundation;
import com.helpit.model.Volunteer;
import com.helpit.posts.repositories.CommentRepository;
import com.helpit.repositories.FoundationRepository;
import com.helpit.repositories.VolunteerRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SubmitCommentController
{
    private CommentRepository commentRepository;
    private VolunteerRepository volunteerRepository;
    private FoundationRepository foundationRepository;

    public SubmitCommentController(CommentRepository commentRepository, VolunteerRepository volunteerRepository, FoundationRepository foundationRepository) {
        this.commentRepository = commentRepository;
        this.volunteerRepository = volunteerRepository;
        this.foundationRepository = foundationRepository;
    }

    @RequestMapping("/add_comment/submit")
    public String getComments(Model model,
                              @RequestParam  String login,
                              @RequestParam  String foundation,
                              @RequestParam  String content)
    {
        Comment c = new Comment();
        c.setContent(content);
        commentRepository.save(c);

        Foundation f = new Foundation();
        f.setName(foundation);
        foundationRepository.save(f);

        Volunteer u = new Volunteer();
        u.setSurname(login);
        volunteerRepository.save(u);

        c.setVolunteer(u);
        c.setFoundation(f);

        u.getComments().add(c);
        f.getComment().add(c);

        foundationRepository.save(f);
        volunteerRepository.save(u);
        commentRepository.save(c);

        model.addAttribute("comments", commentRepository.findAll());
        return "redirect:/add_comment/list";
    }
}
