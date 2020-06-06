package com.helpit.posts.pcmodel;

import lombok.Data;

import javax.persistence.*;
@Data
@Entity
@Table(name = "comments_under_posts")
public class CommentUnderPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Volunteer volunteer;

    @ManyToOne
    private Post post;

    private String content;
}
