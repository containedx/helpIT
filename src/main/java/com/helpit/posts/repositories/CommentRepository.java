package com.helpit.posts.repositories;

import com.helpit.posts.pcmodel.Comment;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comment, Long>
{
}
