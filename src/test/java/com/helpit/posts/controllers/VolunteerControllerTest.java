package com.helpit.posts.controllers;

import com.helpit.model.User;
import com.helpit.model.Volunteer;
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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class VolunteerControllerTest {

    @Mock
    UserRepository userRepository;

    @Mock
    FoundationRepository foundationRepository;

    @Mock
    VolunteerRepository volunteerRepository;

    VolunteerController controller;
    User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        controller = new VolunteerController(userRepository, foundationRepository, volunteerRepository);

        user = new User();
        user.setId(1);
        user.setEmail("a@bla.pl");

        Authentication authentication = new UsernamePasswordAuthenticationToken(user, null);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @Test
    void getVolunteer() throws Exception {
        Volunteer volunteer = new Volunteer();
        volunteer.setId(1);
        user.setVolunteer(volunteer);

        when(userRepository.findByEmail(anyString())).thenReturn(user);

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        mockMvc.perform(MockMvcRequestBuilders.get("/volunteer"))
                .andExpect(status().isOk())
                .andExpect(view().name("/volunteer/show"))
                .andExpect(model().attributeExists("foundations", "articles", "volunteer"));
        
        verify(userRepository,times(1)).findByEmail(anyString());
        verify(foundationRepository,times(1)).findAll();
    }

    @Test
    void getVolunteerEdit() {

    }
}