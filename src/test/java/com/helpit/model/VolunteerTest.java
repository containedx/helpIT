package com.helpit.model;

import com.helpit.posts.model.Comment;
import com.helpit.posts.model.CommentUnderPost;
import com.helpit.posts.model.Post;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class VolunteerTest {

    Volunteer volunteer = new Volunteer();

    @Test
    void getId() {
        volunteer.setId(1);
        assertEquals(1, volunteer.getId());
    }

    @Test
    void getName() {
        volunteer.setName("Marek");
        assertEquals("Marek", volunteer.getName());
    }

    @Test
    void getSurname() {
        volunteer.setSurname("Zawrocki");
        assertEquals("Zawrocki", volunteer.getSurname());
    }

    @Test
    void getComments() {
        Comment comment = new Comment();
        comment.setId(1);
        comment.setContent("This is awesome!");

        Set<Comment> comments = new HashSet<>();
        comments.add(comment);

        volunteer.setComments(comments);

        Set<Comment> returnedComments = volunteer.getComments();
        assertEquals(1, returnedComments.size());
    }

    @Test
    void getPosts() {
        Post post = new Post();
        post.setId(1);
        post.setContent("I want more!");

        Set<Post> posts = new HashSet<>();
        posts.add(post);

        volunteer.setPosts(posts);

        Set<Post> returnedPosts = volunteer.getPosts();
        assertEquals(1, returnedPosts.size());
    }

    @Test
    void getCommentsUnderPost() {
        CommentUnderPost commentUnderPost = new CommentUnderPost();
        commentUnderPost.setId(1);
        commentUnderPost.setContent("It should be better!");

        Set<CommentUnderPost> commentsSet = new HashSet<>();
        commentsSet.add(commentUnderPost);

        volunteer.setCommentsUnderPost(commentsSet);

        Set<CommentUnderPost> returnedComments = volunteer.getCommentsUnderPost();
        assertEquals(1, returnedComments.size());
    }

    @Test
    void getImage() {

    }
}