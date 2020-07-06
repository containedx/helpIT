package com.helpit.model;

import com.helpit.posts.model.Post;
import lombok.Data;

import com.helpit.posts.model.Comment;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "foundations")
public class Foundation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "foundation_id")
    private Integer id;

    @Column(name = "foundation_name")
    @NotNull
    @NotBlank
    private String name;

    @Column(name = "foundation_owner_name")
    @NotNull
    @NotBlank
    private String ownerName;

    @Column(name = "foundation_owner_surname")
    @NotNull
    @NotBlank
    private String ownerSurname;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "foundation_types", joinColumns = @JoinColumn(name = "foundation_id"), inverseJoinColumns = @JoinColumn(name = "type_id"))
    private Type type;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "foundation_foundation_id")
    private Set<Comment> comment = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "foundation_foundation_id")
    private Set<Post> post = new HashSet<>();

    @Lob
    private Byte[] image;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Foundation that = (Foundation) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

}