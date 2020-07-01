package com.helpit.posts.controllers;

import com.helpit.model.Foundation;
import com.helpit.model.User;
import com.helpit.model.Volunteer;
import com.helpit.posts.model.Post;
import com.helpit.posts.repositories.PostRepository;
import com.helpit.repositories.FoundationRepository;
import com.helpit.repositories.UserRepository;
import com.helpit.repositories.VolunteerRepository;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

class SubmitPostControllerTest {

    @Mock
    PostRepository postRepository;
    @Mock
    VolunteerRepository volunteerRepository;
    @Mock
    FoundationRepository foundationRepository;
    @Mock
    UserRepository userRepository;

    SubmitPostController controller;
    User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        controller = new SubmitPostController(postRepository, volunteerRepository, foundationRepository, userRepository);

        user = new User();
        user.setId(1);
        user.setEmail("a@bla.pl");

        Authentication authentication = new UsernamePasswordAuthenticationToken(user, null);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @Test
    void addArticleToFoundation() throws Exception {
        Foundation foundation = new Foundation();
        foundation.setId(1);

        user.setVolunteer(new Volunteer());
        user.getVolunteer().setPosts(new HashSet<Post>());
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        when(userRepository.findByEmail(anyString())).thenReturn(user);
        when(foundationRepository.findById(anyInt())).thenReturn(Optional.of(foundation));

        mockMvc.perform(MockMvcRequestBuilders.get("/charity/1/add_article")
                .param("title", "War destroys Iraq").param("editordata", "Indeed"))
                .andDo(print())
                .andExpect(status().is(302)) //status przekierowania
                .andExpect(view().name("redirect:/charity/1/show"));

        verify(userRepository, times(1)).findByEmail(anyString());
        verify(foundationRepository, times(1)).findById(anyInt());
    }

    @Test
    void addArticleToSelectedFoundation() throws Exception {
        Foundation foundation = new Foundation();
        foundation.setId(1);

        user.setVolunteer(new Volunteer());
        user.getVolunteer().setPosts(new HashSet<Post>());
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        when(userRepository.findByEmail(anyString())).thenReturn(user);
        when(foundationRepository.findById(anyInt())).thenReturn(Optional.of(foundation));

        mockMvc.perform(MockMvcRequestBuilders.get("/article/submit")
                .param("title", "War destroys Iraq")
                .param("editordata", "Indeed")
                .param("selected", "1"))
                .andDo(print())
                .andExpect(status().is(302)) //status przekierowania
                .andExpect(view().name("redirect:/charity/1/show"));

        verify(userRepository, times(1)).findByEmail(anyString());
        verify(foundationRepository, times(1)).findById(anyInt());
    }
}