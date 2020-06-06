package com.helpit.posts.repositories;

import com.helpit.posts.model.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Long> {

}
