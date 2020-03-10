package com.weebindustry.weebjournal.controllers;

import com.weebindustry.weebjournal.dtos.users.UserCreatableDTO;
import com.weebindustry.weebjournal.dtos.users.UserUpdatableDTO;
import com.weebindustry.weebjournal.exceptions.RequestParamValueNotDefinedException;
import com.weebindustry.weebjournal.models.Post;
import com.weebindustry.weebjournal.models.User;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import com.weebindustry.weebjournal.dtos.posts.PostCreatableDTO;
import com.weebindustry.weebjournal.dtos.posts.PostUpdatableDTO;
import com.weebindustry.weebjournal.models.Comment;
import com.weebindustry.weebjournal.util.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/")
public class PostController {

    private final HelperServiceOneToMany<Post> service;

    private final HelperService<Post> singleRepositoryService;

    public PostController(HelperServiceOneToMany<Post> service, HelperService<Post> singleRepositoryService) {
        this.service = service;
        this.singleRepositoryService = singleRepositoryService;
    }


    @GetMapping(path = "/posts", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> findAllPosts(Pageable pageable,@RequestParam(required = false, defaultValue = "false") String hasPageable) {
        if (hasPageable.equals("true")) {
            return ResponseEntity.ok(singleRepositoryService.findAll(pageable));
        }
        else if(hasPageable.equals("false")) {
            return ResponseEntity.ok(singleRepositoryService.list());
        }
        else {
            throw new RequestParamValueNotDefinedException("hasPageable", hasPageable);
        }

    }

    @GetMapping(path = "/posts/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Post> findPostById(@PathVariable(value = "id") Long id) {
        Post post = singleRepositoryService.findById(id);
        return ResponseEntity.ok(post);
    }

    @PostMapping("/posts")
    public ResponseEntity<Post> createPost(@Valid @RequestBody @DTO(PostCreatableDTO.class) Post post) {
        return ResponseEntity.ok(singleRepositoryService.create(post));
    }

    @PutMapping("posts/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable Long id, @Valid @RequestBody @DTO(PostUpdatableDTO.class) Post post) {
        return ResponseEntity.ok(singleRepositoryService.update(id, post));
    }


    @DeleteMapping("posts/{id}")
    public ResponseEntity<?> deletePost(@PathVariable(value = "id") Long id) {
        singleRepositoryService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/users/{id}/posts")
    public ResponseEntity<Page<Post>> findAllPostsByUserId(@PathVariable(value = "id") Long userId, Pageable pageable) {
        return ResponseEntity.ok(service.getManyByOne(userId, pageable));
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
    public ResponseEntity<?> deleteComment(@PathVariable(value = "userId") Long userId,
            @PathVariable(value = "postId") Long postId) {
        service.delete(userId, postId);

        return ResponseEntity.ok().build();
    }
}