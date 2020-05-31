package com.helpit.foundation.repositories;

import com.helpit.foundation.model.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Long> {
}
