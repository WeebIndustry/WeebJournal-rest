package com.weebindustry.weebjournal.repositories;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

import com.weebindustry.weebjournal.models.Comment;

import com.weebindustry.weebjournal.models.Post;
import com.weebindustry.weebjournal.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    
    List<Comment> findByPost(Post post);

    List<Comment> findAllByUser(User user);
}