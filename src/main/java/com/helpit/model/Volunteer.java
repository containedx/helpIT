package com.helpit.model;

import com.helpit.posts.model.Comment;
import com.helpit.posts.model.CommentUnderPost;
import com.helpit.posts.model.Post;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;


@Data
@Entity
@Table(name ="volunteers")
public class Volunteer{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "volunteer_id")
  private Integer id;

  @Column(name="volunteer_name")
  @NotNull
  @NotBlank
  private String name;

  @Column(name="volunteer_surname")
  @NotNull
  @NotBlank
  private String surname;

  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "volunteer_volunteer_id")
  private Set<Comment> comments = new HashSet<>();

  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "volunteer_volunteer_id")
  private Set<Post> posts = new HashSet<>();

  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "volunteer_id")
  private Set<CommentUnderPost> commentsUnderPost = new HashSet<>();

  @Lob
  private Byte[] image;
}
