package com.helpit.posts.model;

import com.helpit.model.Foundation;
import com.helpit.model.Volunteer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommentTest {

    Comment comment = new Comment();

    @Test
    void getId() {
        comment.setId(1);
        assertEquals(1, comment.getId());
    }

    @Test
    void getVolunteer() {
        Volunteer volunteer = new Volunteer();
        volunteer.setId(3);

        comment.setVolunteer(volunteer);

        assertNotNull(comment.getVolunteer());
        assertEquals(3, comment.getVolunteer().getId());
    }

    @Test
    void getFoundation() {
        Foundation foundation = new Foundation();
        foundation.setId(2);

        comment.setFoundation(foundation);

        assertNotNull(comment.getFoundation());
        assertEquals(2, comment.getFoundation().getId());
    }

    @Test
    void getContent() {
        comment.setContent("Are U aware of that?");
        assertEquals("Are U aware of that?", comment.getContent());
    }
}