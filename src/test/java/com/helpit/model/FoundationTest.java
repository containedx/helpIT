package com.helpit.model;

import com.helpit.posts.model.Comment;
import com.helpit.posts.model.Post;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class FoundationTest {

    private Foundation foundation = new Foundation();

    @Test
    void getId() {
        foundation.setId(1);
        assertEquals(1, foundation.getId());
    }

    @Test
    void getName() {
        foundation.setName("Polsat");
        assertEquals("Polsat", foundation.getName());
    }

    @Test
    void getOwnerName() {
        foundation.setOwnerName("Jan");
        assertEquals("Jan", foundation.getOwnerName());
    }

    @Test
    void getOwnerSurname() {
        foundation.setOwnerSurname("Wańkowicz");
        assertEquals("Wańkowicz", foundation.getOwnerSurname());
    }

    @Test
    void getType() {
        Type type  = new Type();
        type.setId(1);
        type.setType("Foundation");

        foundation.setType(type);

        Type returnedType = foundation.getType();
        assertEquals("Foundation", returnedType.getType());
    }

    @Test
    void getComment() {
        Comment comment = new Comment();
        comment.setId(1);
        comment.setContent("This is awesome!");

        Set<Comment> comments = new HashSet<>();
        comments.add(comment);

        foundation.setComment(comments);

        Set<Comment> returnedComments = foundation.getComment();
        assertEquals(1, returnedComments.size());
    }

    @Test
    void getPost() {
        Post post = new Post();
        post.setId(1);
        post.setContent("I want more!");

        Set<Post> posts = new HashSet<>();
        posts.add(post);

        foundation.setPost(posts);

        Set<Post> returnedPosts = foundation.getPost();
        assertEquals(1, returnedPosts.size());
    }

    @Test
    void getImage() {

    }
}