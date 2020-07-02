package com.helpit.posts.controllers;

import com.helpit.model.Foundation;
import com.helpit.posts.model.Comment;
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
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class FoundationControllerTest {

    @Mock
    FoundationRepository foundationRepository;

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

    @Test
    void getCharityOpinions() throws Exception {
        Foundation foundation = new Foundation();
        foundation.setId(1);
        foundation.setComment(new HashSet<>());
        Comment c1 = new Comment();
        Comment c2 = new Comment();
        c1.setId(1);
        c1.setRate(5);
        c2.setId(2);
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
}