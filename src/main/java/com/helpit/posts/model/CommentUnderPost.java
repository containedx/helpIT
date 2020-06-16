package com.helpit.posts.model;

import com.helpit.model.Volunteer;
import lombok.Data;

import javax.persistence.*;
@Data
@Entity
@Table(name = "comments_under_posts")
public class CommentUnderPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Volunteer volunteer;

    @ManyToOne
    private Post post;

    private String content;
}
