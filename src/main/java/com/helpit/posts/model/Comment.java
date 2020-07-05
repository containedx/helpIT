package com.helpit.posts.model;

import com.helpit.model.Foundation;
import com.helpit.model.Volunteer;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "comments")
public class Comment
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Volunteer volunteer;

    @ManyToOne
    private Foundation foundation;

    private String content;

    private Integer rate;

    @CreationTimestamp
    @Column(name = "create_timestamp")
    private LocalDateTime createTime;

    @UpdateTimestamp
    @Column(name = "update_timestamp")
    private LocalDateTime updateTime;

    public Comment()
    {
    }

    public Comment(String content)
    {
        this.content = content;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Comment comment = (Comment) o;

        return id != null ? id.equals(comment.id) : comment.id == null;
    }

    @Override
    public int hashCode()
    {
        return id != null ? id.hashCode() : 0;
    }

}