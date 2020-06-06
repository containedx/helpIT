package com.helpit.posts.repositories;

import com.helpit.posts.model.CommentUnderPost;
import org.springframework.data.repository.CrudRepository;

public interface CommentUnderPostRepository extends CrudRepository<CommentUnderPost, Long> {
}
