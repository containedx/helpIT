package com.helpit.foundation.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Foundation
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "foundation_id")
    private Set<Comment> comment = new HashSet<>();

    public Foundation()
    {
    }

    public Foundation(String name)
    {
        this.name = name;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Set<Comment> getComment()
    {
        return comment;
    }

    public void setComment(Set<Comment> comment_set)
    {
        this.comment = comment_set;
    }

    @Override
    public String toString()
    {
        return "Foundation{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", comment_set=" + comment +
                '}';
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Foundation that = (Foundation) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode()
    {
        return id != null ? id.hashCode() : 0;
    }
}
