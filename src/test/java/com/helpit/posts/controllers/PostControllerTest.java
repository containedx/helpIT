package com.helpit.posts.controllers;

import com.helpit.posts.model.Post;
import com.helpit.posts.repositories.PostRepository;
import com.helpit.repositories.FoundationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

class PostControllerTest {


    @Mock
    PostRepository postRepository;

    @Mock
    FoundationRepository foundationRepository;

    PostController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        controller = new PostController(postRepository, foundationRepository);
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
    void getPostAdd() {

    }
}