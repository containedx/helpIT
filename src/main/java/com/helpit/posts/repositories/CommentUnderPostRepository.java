package com.helpit.posts.repositories;

import com.helpit.posts.model.CommentUnderPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentUnderPostRepository extends JpaRepository<CommentUnderPost, Integer> {
}
