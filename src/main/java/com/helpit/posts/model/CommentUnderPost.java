package com.helpit.posts.model;

import com.helpit.model.Volunteer;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

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

    @CreationTimestamp
    @Column(name = "create_timestamp")
    private LocalDateTime createTime;

    @UpdateTimestamp
    @Column(name = "update_timestamp")
    private LocalDateTime updateTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CommentUnderPost that = (CommentUnderPost) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
