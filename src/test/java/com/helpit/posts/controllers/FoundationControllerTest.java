package com.helpit.posts.controllers;

import com.helpit.model.Foundation;
import com.helpit.model.User;
import com.helpit.model.Volunteer;
import com.helpit.posts.model.Comment;
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

import java.time.LocalDateTime;
import java.util.*;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class FoundationControllerTest {

    @Mock
    FoundationRepository foundationRepository;

    @Mock
    UserRepository userRepository;

    @Mock
    PostRepository postRepository;

    FoundationController controller;
    User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        controller = new FoundationController(foundationRepository, userRepository, postRepository);

        user = new User();
        user.setId(1);
        user.setEmail("a@bla.pl");

        Authentication authentication = new UsernamePasswordAuthenticationToken(user, null);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @Test
    void getCharityShow() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        mockMvc.perform(MockMvcRequestBuilders.get("/charity/show"))
                .andExpect(status().isOk())
                .andExpect(view().name("/charity/show_default"));
    }

    @Test
    void testGetCharityShow() throws Exception {
        Foundation foundation = new Foundation();
        foundation.setId(1);

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        when(foundationRepository.findById(anyInt())).thenReturn(Optional.of(foundation));

        mockMvc.perform(MockMvcRequestBuilders.get("/charity/1/show"))
                .andExpect(status().isOk())
                .andExpect(view().name("/charity/show"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("foundation", "articles"));
    }

    @Test
    void getFoundList() throws Exception {
        Foundation foundation = new Foundation();
        foundation.setId(1);

        List<Foundation> foundations = new LinkedList<>();
        foundations.add(foundation);

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        when(foundationRepository.findAll()).thenReturn(foundations);

        mockMvc.perform(MockMvcRequestBuilders.get("/charities"))
                .andExpect(status().isOk())
                .andExpect(view().name("/charity/list"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("foundations"));
    }

    @Test
    void getCharityOpinions() throws Exception {
        Foundation foundation = new Foundation();
        foundation.setId(1);
        foundation.setComment(new HashSet<>());
        Comment c1 = new Comment();
        Comment c2 = new Comment();
        c1.setId(1);
        c1.setCreateTime(LocalDateTime.of(1993,1,22,22,1));
        c1.setRate(5);
        c2.setId(2);
        c2.setCreateTime(LocalDateTime.of(1993,1,23,22,1));
        c2.setRate(4);
        foundation.getComment().add(c1);
        foundation.getComment().add(c2);

        when(foundationRepository.findById(anyInt())).thenReturn(Optional.of(foundation));

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        mockMvc.perform(MockMvcRequestBuilders.get("/charity/1/opinion"))
                .andExpect(status().isOk())
                .andExpect(view().name("/charity/opinions"))
                .andExpect(model().attributeExists("rating", "foundation"));

        verify(foundationRepository, times(1)).findById(anyInt());
    }

    @Test
    void getFoundation() throws Exception {
        Foundation foundation = new Foundation();
        foundation.setId(1);
        foundation.setPost(new HashSet<>());
        user.setFoundation(foundation);

        when(userRepository.findByEmail(anyString())).thenReturn(user);

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        mockMvc.perform(MockMvcRequestBuilders.get("/foundation"))
                .andExpect(status().isOk())
                .andExpect(view().name("/foundation/show"))
                .andExpect(model().attributeExists("foundation", "articles"));

        verify(userRepository,times(1)).findByEmail(anyString());
    }
}