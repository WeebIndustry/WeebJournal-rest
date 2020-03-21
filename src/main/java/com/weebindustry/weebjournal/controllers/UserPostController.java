package com.weebindustry.weebjournal.controllers;

import com.weebindustry.weebjournal.dtos.posts.PostCreatableDTO;
import com.weebindustry.weebjournal.dtos.posts.PostUpdatableDTO;
import com.weebindustry.weebjournal.exceptions.RequestParamValueNotDefinedException;
import com.weebindustry.weebjournal.models.Post;
import com.weebindustry.weebjournal.repositories.PostRepository;
import com.weebindustry.weebjournal.util.DTO;
import com.weebindustry.weebjournal.util.HelperServiceOneToMany;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/")
public class UserPostController {


    private final HelperServiceOneToMany<Post> service;

    private final PostRepository postRepository;

    @Autowired
    public UserPostController(@Qualifier("UserPostService") HelperServiceOneToMany<Post> service, PostRepository postRepository) {
        this.service = service;
        this.postRepository = postRepository;
    }

    @GetMapping("/users/{id}/posts")
    public ResponseEntity<?> findAllPostsByUserId(@PathVariable(value = "id") Long userId, Pageable pageable, @RequestParam(required = false, defaultValue = "false") String hasPageable) {
        if (hasPageable.equals("true")) {
            return ResponseEntity.ok(postRepository.findByUserId(userId, pageable));
        }
        else if (hasPageable.equals("false")) {
            return ResponseEntity.ok(postRepository.findByUserIdWithoutPageable(userId));
        }
        else {
            throw new RequestParamValueNotDefinedException("hasPageable", hasPageable);
        }
    }

    public ResponseEntity<?> findAllPostsByUserName(@PathVariable(value = "username") String username, Pageable pageable, @RequestParam(required = false, defaultValue = "false") String hasPageable) {
        if (hasPageable.equals("true")) {
            return ResponseEntity.ok(postRepository.findByUserName(username, pageable));
        }
        else if (hasPageable.equals("false")) {
            return ResponseEntity.ok(postRepository.findByUserNameWithoutPageable(username));
        }
        else {
            throw new RequestParamValueNotDefinedException("hasPageable", hasPageable);
        }
    }

    @GetMapping("/users/{id}/postcount")
    public ResponseEntity<Long> countPostByUserId(@PathVariable(value = "id") Long userId) {
        return ResponseEntity.ok(postRepository.countByUserId(userId));
    }

    @PostMapping("/users/{id}/posts")
    public ResponseEntity<Post> createPost(@PathVariable(value = "id") Long userId,
                                           @Valid @RequestBody @DTO(PostCreatableDTO.class) Post post) {
        return ResponseEntity.ok(service.create(userId, post));
    }

    @PutMapping("/users/{userId}/posts/{postId}")
    public ResponseEntity<Post> updatePost(@PathVariable(value = "userId") Long userId,
                                           @PathVariable(value = "postId") Long postId, @Valid @RequestBody @DTO(PostUpdatableDTO.class) Post post) {
        return ResponseEntity.ok(service.update(userId, postId, post));
    }

    @DeleteMapping("users/{userId}/posts/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable(value = "userId") Long userId,
                                        @PathVariable(value = "postId") Long postId) {
        service.delete(userId, postId);

        return ResponseEntity.ok().build();
    }
}
