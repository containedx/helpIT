package com.helpit.posts.controllers;

import com.helpit.model.User;
import com.helpit.model.Volunteer;
import com.helpit.posts.model.CommentUnderPost;
import com.helpit.posts.model.Post;
import com.helpit.posts.repositories.CommentUnderPostRepository;
import com.helpit.posts.repositories.PostRepository;
import com.helpit.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class SubmitCommentUnderPostControllerTest {

    @Mock
    CommentUnderPostRepository commentUnderPostRepository;
    @Mock
    PostRepository postRepository;
    @Mock
    UserRepository userRepository;


    SubmitCommentUnderPostController controller;
    User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        controller = new SubmitCommentUnderPostController(commentUnderPostRepository, postRepository, userRepository);
        user = new User();
        user.setId(1);
        user.setEmail("a@bla.pl");

        Authentication authentication = new UsernamePasswordAuthenticationToken(user, null);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @Test
    void addArticleToFoundation() throws Exception {

        Volunteer volunteer = new Volunteer();
        volunteer.setId(1);

        Set<CommentUnderPost> comments = new HashSet<>();

        volunteer.setCommentsUnderPost(comments);
        user.setVolunteer(volunteer);

        Post post = new Post();
        post.setId(1);

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        when(userRepository.findByEmail(anyString())).thenReturn(user);
        when(postRepository.findById(anyInt())).thenReturn(Optional.of(post));

        mockMvc.perform(MockMvcRequestBuilders.get("/article/1/add_comment")
                .param("editordata", "dziala"))
//                .andDo(print())
                .andExpect(status().is(302)) //status przekierowania
                .andExpect(view().name("redirect:/article/1/show"));

        verify(userRepository, times(1)).findByEmail(anyString());
        verify(postRepository, times(1)).findById(anyInt());

    }
}