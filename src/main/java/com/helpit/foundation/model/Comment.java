package com.helpit.foundation.model;

import javax.persistence.*;

@Entity
public class Comment
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private User author;

    @ManyToOne
    private Foundation foundation;

    private String content;

    public Comment()
    {
    }

    public Comment(String content)
    {
        this.content = content;
    }

    public User getAuthor()
    {
        return author;
    }

    public void setAuthor(User author)
    {
        this.author = author;
    }

    public Foundation getFoundation()
    {
        return foundation;
    }

    public void setFoundation(Foundation foundation)
    {
        this.foundation = foundation;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    @Override
    public String toString()
    {
        return "Comment{" +
                "id=" + id +
                ", author=" + author +
                ", foundation=" + foundation +
                ", content='" + content + '\'' +
                '}';
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
