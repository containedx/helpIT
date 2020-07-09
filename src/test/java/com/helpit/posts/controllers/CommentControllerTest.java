package com.helpit.posts.controllers;

import com.helpit.posts.repositories.CommentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.*;

class CommentControllerTest {

    @Mock
    private CommentRepository commentRepository;

    @Mock
    Model model;

    CommentController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        controller = new CommentController(commentRepository);
    }

    @Test
    void getAddComment() {

    }

    @Test
    void getListComment() {

    }
}