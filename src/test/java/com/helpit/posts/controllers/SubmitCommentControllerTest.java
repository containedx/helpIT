package com.helpit.posts.controllers;

import com.helpit.model.Foundation;
import com.helpit.model.User;
import com.helpit.model.Volunteer;
import com.helpit.posts.model.Comment;
import com.helpit.posts.repositories.CommentRepository;
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

class SubmitCommentControllerTest {

    @Mock
    CommentRepository commentRepository;
    @Mock
    UserRepository userRepository;
    @Mock
    FoundationRepository foundationRepository;

    SubmitCommentController controller;
    User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        controller = new SubmitCommentController(commentRepository, userRepository, foundationRepository);

        user = new User();
        user.setId(1);
        user.setEmail("a@bla.pl");

        Authentication authentication = new UsernamePasswordAuthenticationToken(user, null);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @Test
    void submitOpinion() throws Exception {
        Volunteer volunteer = new Volunteer();
        volunteer.setId(1);
        volunteer.setComments(new HashSet<Comment>());
        user.setVolunteer(volunteer);

        Foundation foundation = new Foundation();
        foundation.setId(2);

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        when(userRepository.findByEmail(anyString())).thenReturn(user);
        when(foundationRepository.findById(anyInt())).thenReturn(Optional.of(foundation));

        mockMvc.perform(MockMvcRequestBuilders.get("/charity/1/submitopinion")
        .param("editordata", "spring to fajna sprawa")
        .param("rating", "5"))
                .andDo(print())
                .andExpect(status().is(302)) //przekierowanie
        .andExpect(view().name("redirect:/charity/1/opinion"));

        verify(userRepository, times(1)).findByEmail(anyString());
        verify(foundationRepository, times(1)).findById(anyInt());
    }
}