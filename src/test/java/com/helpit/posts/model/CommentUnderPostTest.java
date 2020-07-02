package com.helpit.posts.model;

import com.helpit.model.Volunteer;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.parameters.P;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class CommentUnderPostTest {

    CommentUnderPost commentUnderPost = new CommentUnderPost();

    @Test
    void getId() {
        commentUnderPost.setId(1);
        assertEquals(1, commentUnderPost.getId());
    }

    @Test
    void getVolunteer() {
        Volunteer volunteer = new Volunteer();
        volunteer.setId(2);

        commentUnderPost.setVolunteer(volunteer);

        assertNotNull(commentUnderPost.getVolunteer());
        assertEquals(2, commentUnderPost.getVolunteer().getId());
    }

    @Test
    void getPost() {
        Post post = new Post();
        post.setId(1);

        commentUnderPost.setPost(post);

        assertNotNull(commentUnderPost.getPost());
        assertEquals(1, commentUnderPost.getPost().getId());
    }

    @Test
    void getContent() {
        commentUnderPost.setContent("See ya soon!");
        assertEquals("See ya soon!", commentUnderPost.getContent());
    }

    @Test
    void getCreateTime() {
        LocalDateTime cur = LocalDateTime.of(1994, 11, 2, 2, 11);
        commentUnderPost.setCreateTime(cur);

        assertEquals(cur, commentUnderPost.getCreateTime());
    }

    @Test
    void getUpdateTime() {
        LocalDateTime cur = LocalDateTime.of(1994, 11, 2, 2, 11);
        commentUnderPost.setUpdateTime(cur);

        assertEquals(cur, commentUnderPost.getUpdateTime());
    }
}