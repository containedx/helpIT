package com.helpit.foundation.model;


import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;

import java.util.Set;

@Data
@Entity
@Table(name = "volunteers")
public class Volunteer
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String login;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "volunteer_id")
    private Set<Comment> comments = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "volunteer_id")
    private Set<Post> posts = new HashSet<>();

    @OneToOne
    @JoinColumn(name = "volunteer_id")
    private CommentUnderPost commentUnderPost;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Volunteer volunteer = (Volunteer) o;

        return id != null ? id.equals(volunteer.id) : volunteer.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
