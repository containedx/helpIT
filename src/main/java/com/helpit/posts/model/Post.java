package com.helpit.posts.model;

import com.helpit.model.Foundation;
import com.helpit.model.Types;
import com.helpit.model.Volunteer;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "posts")
public class Post{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Volunteer volunteer;

    @ManyToOne
    private Foundation foundation;

    private String title;

    @Lob
    private String content;

    @CreationTimestamp
    @Column(name = "create_timestamp")
    private LocalDateTime createTime;

    @UpdateTimestamp
    @Column(name = "update_timestamp")
    private LocalDateTime updateTime;

    @Enumerated(value = EnumType.STRING)
    private Types category;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "post_id")
    private Set<CommentUnderPost> commentsUnderPost = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Post post = (Post) o;

        return id != null ? id.equals(post.id) : post.id == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (id != null ? id.hashCode() : 0);
        return result;
    }

}
