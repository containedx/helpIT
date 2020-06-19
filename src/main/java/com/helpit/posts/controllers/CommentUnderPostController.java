package com.helpit.posts.controllers;

import com.helpit.posts.model.CommentUnderPost;
import com.helpit.posts.model.Post;
import com.helpit.model.Volunteer;
import com.helpit.posts.repositories.CommentUnderPostRepository;
import com.helpit.posts.repositories.PostRepository;
import com.helpit.repositories.VolunteerRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class CommentUnderPostController {

    private final PostRepository postRepository;
    private final CommentUnderPostRepository commentUnderPostRepository;
    private final VolunteerRepository volunteerRepository;

    public CommentUnderPostController(PostRepository postRepository, CommentUnderPostRepository commentUnderPostRepository, VolunteerRepository volunteerRepository) {
        this.postRepository = postRepository;
        this.commentUnderPostRepository = commentUnderPostRepository;
        this.volunteerRepository = volunteerRepository;
    }

    @RequestMapping({"/add_comment/{id}/add_2_post"})
    public String addCommentUnderPost(@PathVariable String id, Model model) {

        model.addAttribute("post", id);
        return "/add_comment/add_2_post";
    }

    @RequestMapping({"/add_comment_2_post/{id}"})
    public String getCommentsUnderPost(@PathVariable String id,
                                       @RequestParam  String content,
                                       Model model) {
        Optional<Post> post = postRepository.findById(Integer.valueOf(id));
        if( post.isPresent() ) {
            CommentUnderPost cp = new CommentUnderPost();
            Volunteer v = new Volunteer();
            volunteerRepository.save(v);

            v.setSurname("janek");
            try {
                Post p = postRepository.findById(Integer.valueOf(id)).get();
                cp.setPost(p);
                cp.setVolunteer(v);
                cp.setContent(content);
                commentUnderPostRepository.save(cp);
                model.addAttribute("comments", p.getCommentsUnderPost());
            }
            catch (Exception e) {
                throw new RuntimeException("Sth went wrong");
            }


        }
        else {
            throw new RuntimeException("Cannot add to post");
        }

        return "/add_comment/show_comments_under_post";
    }
}
