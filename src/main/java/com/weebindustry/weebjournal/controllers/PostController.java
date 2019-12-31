package com.weebindustry.weebjournal.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.weebindustry.weebjournal.models.Post;
import com.weebindustry.weebjournal.repositories.*;

@RestController
public class PostController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private PostRepository repo;

    @Autowired
    private UserRepository userRepo;

    @GetMapping("/users/{id}/posts")
    public ResponseEntity<Page<Post>> findAllPostsByUserId(@PathVariable(value="id") Long userId, Pageable pageable) {
        return ResponseEntity.ok(repo.findByUserId(userId, pageable));
    }

    @PostMapping("/users/{id}/posts")
    public ResponseEntity<Post> createPost(@PathVariable(value = "id") Long userId, @Valid @RequestBody Post post) {
        return userRepo.findById(userId).map(user -> {
            post.setUser(user);
            return ResponseEntity.ok(repo.save(post));
        }).orElse(null);
    }

    @PutMapping("/users/{userId}/posts/{postId}")
    public ResponseEntity<Post> updatePost(@PathVariable(value="userId") Long userId, @PathVariable(value="postId") Long postId, @Valid @RequestBody Post post) {
        if (!userRepo.findById(userId).isPresent()) {
            log.error("User Id {} doesn't exist!", userId);
            ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(repo.findById(postId).map(comment -> {
            return repo.save(post);
        }).orElse(null));
    }

    @DeleteMapping("users/{userId}/posts/{postId}")
    public ResponseEntity<?> deleteComment(@PathVariable(value = "userId") Long userId, @PathVariable(value = "postId") Long postId) {
        return repo.findByIdAndUserId(postId, userId).map(post -> {
            repo.delete(post);
            return ResponseEntity.ok().build();
        }).orElse(null);
    }
}