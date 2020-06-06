package com.helpit.posts.repositories;

import com.helpit.posts.pcmodel.CommentUnderPost;
import org.springframework.data.repository.CrudRepository;

public interface CommentUnderPostRepository extends CrudRepository<CommentUnderPost, Long> {
}
