package com.weebindustry.weebjournal.repositories;

import java.util.Optional;

import com.weebindustry.weebjournal.models.Post;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>{
    Page<Post> findByUserId(Long userId, Pageable pageable);
    Optional<Post> findByIdAndUserId(Long id, Long userId);
    
}