package com.helpit.posts.controllers;

import com.helpit.model.User;
import com.helpit.model.Volunteer;
import com.helpit.posts.model.Post;
import com.helpit.posts.repositories.PostRepository;
import com.helpit.repositories.FoundationRepository;
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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class PostControllerTest {


    @Mock
    PostRepository postRepository;

    @Mock
    FoundationRepository foundationRepository;

    @Mock
    UserRepository userRepository;

    PostController controller;
    User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        controller = new PostController(postRepository, foundationRepository, userRepository);

        user = new User();
        user.setId(1);
        user.setEmail("a@bla.pl");

        Authentication authentication = new UsernamePasswordAuthenticationToken(user, null);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @Test
    void getPostShow() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        mockMvc.perform(MockMvcRequestBuilders.get("/article/show"))
                .andExpect(status().isOk())
                .andExpect(view().name("/article/show_default"));
    }

    @Test
    void getPostShowById() throws Exception {
        Post post = new Post();
        post.setId(1);

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        when(postRepository.findById(anyInt())).thenReturn(Optional.of(post));

        mockMvc.perform(MockMvcRequestBuilders.get("/article/1/show"))
                .andExpect(status().isOk())
                .andExpect(view().name("/article/show"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("article"));
    }

    @Test
    void getPostEdit() {

    }

    @Test
    void getPostAdd() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        mockMvc.perform(MockMvcRequestBuilders.get("/article/adding"))
                .andExpect(status().isOk())
                .andExpect(view().name("/article/add"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("foundations"));
    }

    @Test
    void deletePost() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        mockMvc.perform(MockMvcRequestBuilders.get("/article/1/delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/volunteer"));

        verify(postRepository,times(1)).deleteById(anyInt());
    }

    @Test
    void filterArticles() throws Exception {
        Volunteer volunteer = new Volunteer();
        volunteer.setId(1);
        user.setVolunteer(volunteer);

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        when(userRepository.findByEmail(anyString())).thenReturn(user);

        mockMvc.perform(MockMvcRequestBuilders.get("/article/filter")
                .param("category", "disease")
                .param("foundation", "1"))
                .andExpect(status().isOk())
                .andExpect(view().name("volunteer/show"))
                .andExpect(model().attributeExists("foundations", "articles", "volunteer"));

        verify(postRepository,times(1)).findAll();
        verify(userRepository,times(1)).findByEmail(anyString());
        verify(foundationRepository,times(1)).findAll();
    }
}