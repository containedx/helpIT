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


}
