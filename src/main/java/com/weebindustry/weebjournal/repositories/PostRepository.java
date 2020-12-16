package com.weebindustry.weebjournal.repositories;

import java.util.List;

import com.weebindustry.weebjournal.models.Board;
import com.weebindustry.weebjournal.models.Post;

import com.weebindustry.weebjournal.models.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByBoard(Board board);

    List<Post> findByUser(User user);
}