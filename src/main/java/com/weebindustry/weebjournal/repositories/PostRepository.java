package com.weebindustry.weebjournal.repositories;

import com.weebindustry.weebjournal.models.Post;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long>{
 
    
}