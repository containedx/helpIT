package com.helpit.posts.controllers;

import com.helpit.model.Foundation;
import com.helpit.repositories.FoundationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

class FoundationControllerTest {

    @Mock
    FoundationRepository foundationRepository;

    @Mock
    Model model;

    FoundationController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        controller = new FoundationController(foundationRepository);
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
}