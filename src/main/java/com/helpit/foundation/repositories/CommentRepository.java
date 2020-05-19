package com.helpit.foundation.repositories;

import com.helpit.foundation.model.Comment;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comment, Long>
{
}
