package com.weebindustry.weebjournal.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.weebindustry.weebjournal.models.Post;
import com.weebindustry.weebjournal.repositories.*;

@RestController
@RequestMapping("/posts")
public class PostController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private PostRepository repo;


    @GetMapping("/")
    public ResponseEntity<List<Post>> findAll() {
        return ResponseEntity.ok(repo.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> findById(@PathVariable(value = "id") Long id) {
        
        Optional<Post> result = repo.findById(id);

        if (!result.isPresent()) {
            log.error("Post Id {} doesn't exist", id);
            ResponseEntity.badRequest().build();
        }
        
        return ResponseEntity.ok(result.get());
        
    }

    @PostMapping("/")
    public ResponseEntity<Post> createPost(@Valid @RequestBody Post post) {
        return ResponseEntity.ok(repo.save(post));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable Long id, @Valid @RequestBody Post post) {
        if (!repo.findById(id).isPresent()) {
            log.error("Post Id {} doesn't exist", id);
            ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(repo.save(post));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePost(@PathVariable(value = "id") Long id)
    {
        if (!repo.findById(id).isPresent()) {
            log.error("User Id {} doesn't exist!", id);
            ResponseEntity.badRequest().build();
        }

        repo.deleteById(id);

        return ResponseEntity.ok().build();
    }
}