package com.weebindustry.weebjournal.controllers;

import com.weebindustry.weebjournal.exceptions.RequestParamValueNotDefinedException;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import com.weebindustry.weebjournal.dtos.posts.PostCreatableDTO;
import com.weebindustry.weebjournal.dtos.posts.PostUpdatableDTO;
import com.weebindustry.weebjournal.models.Post;
import com.weebindustry.weebjournal.util.*;

@RestController
@RequestMapping("/api/")
public class PostController {

    private final HelperServiceOneToMany<Post> service;

    private final HelperService<Post> singleRepositoryService;

    public PostController(HelperServiceOneToMany<Post> service, HelperService<Post> singleRepositoryService) {
        this.service = service;
        this.singleRepositoryService = singleRepositoryService;
    }

    @GetMapping("/posts")
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