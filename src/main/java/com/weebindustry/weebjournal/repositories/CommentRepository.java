package com.weebindustry.weebjournal.repositories;

import java.awt.print.Pageable;
import java.util.Optional;

import com.weebindustry.weebjournal.models.Comment;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    
    Page<Comment> findByPostId(Long postId, org.springframework.data.domain.Pageable pageable);
    Optional<Comment> findByIdAndPostId(Long id, Long postId);
    
}