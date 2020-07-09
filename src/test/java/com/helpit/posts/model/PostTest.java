package com.helpit.posts.model;

import com.helpit.model.Foundation;
import com.helpit.model.Types;
import com.helpit.model.Volunteer;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class PostTest {

    Post post = new Post();

    @Test
    void getId() {
        post.setId(1);
        assertEquals(1, post.getId());
    }

    @Test
    void getVolunteer() {
        Volunteer volunteer = new Volunteer();
        volunteer.setId(1);
        post.setVolunteer(volunteer);

        assertNotNull(post.getVolunteer());
        assertEquals(1, post.getVolunteer().getId());
    }

    @Test
    void getFoundation() {
        Foundation foundation = new Foundation();
        foundation.setId(1);
        post.setFoundation(foundation);

        assertNotNull(post.getFoundation());
        assertEquals(1, post.getFoundation().getId());
    }

    @Test
    void getTitle() {
        post.setTitle("Is this war just?");
        assertEquals("Is this war just?", post.getTitle());
    }

    @Test
    void getContent() {
        post.setContent("US troop took Baghdad after weeks of siege!");
        assertEquals("US troop took Baghdad after weeks of siege!", post.getContent());
    }

    @Test
    void getCommentsUnderPost() {
        CommentUnderPost commentUnderPost = new CommentUnderPost();
        commentUnderPost.setId(5);
        Set<CommentUnderPost> comments = new HashSet<>();
        comments.add(commentUnderPost);

        post.setCommentsUnderPost(comments);

        assertNotNull(post.getCommentsUnderPost());
        assertEquals(1, post.getCommentsUnderPost().size());
    }

    @Test
    void getCreateTime() {
        LocalDateTime cur = LocalDateTime.of(1994, 11, 2, 2, 11);
        post.setCreateTime(cur);

        assertEquals(cur, post.getCreateTime());
    }

    @Test
    void getUpdateTime() {
        LocalDateTime cur = LocalDateTime.of(1994, 11, 2, 2, 11);
        post.setUpdateTime(cur);

        assertEquals(cur, post.getUpdateTime());
    }

    @Test
    void getCategory() {
        post.setCategory(Types.culture);
        assertEquals(Types.culture, post.getCategory());
    }
}