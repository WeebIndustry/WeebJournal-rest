package com.weebindustry.weebjournal.controllers;

import com.weebindustry.weebjournal.dtos.comments.CommentCreatableDTO;
import com.weebindustry.weebjournal.dtos.comments.CommentUpdatableDTO;
import com.weebindustry.weebjournal.dtos.posts.PostCreatableDTO;
import com.weebindustry.weebjournal.dtos.posts.PostUpdatableDTO;
import com.weebindustry.weebjournal.exceptions.RequestParamValueNotDefinedException;
import com.weebindustry.weebjournal.models.Comment;
import com.weebindustry.weebjournal.models.Post;
import com.weebindustry.weebjournal.util.DTO;
import com.weebindustry.weebjournal.util.HelperService;
import com.weebindustry.weebjournal.util.HelperServiceOneToMany;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/")
public class CommentController {

    @Autowired
    private HelperServiceOneToMany<Comment> commentService;

    @Autowired
    private HelperService<Comment> singleRepositoryService;

    @GetMapping(path = "/comments", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> findAllComments(Pageable pageable,@RequestParam(required = false, defaultValue = "false") String hasPageable) {
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

    @GetMapping(path = "/comments/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Comment> findCommentById(@PathVariable(value = "id") Long id) {
        Comment post = singleRepositoryService.findById(id);
        return ResponseEntity.ok(post);
    }

    @PostMapping("/comments")
    public ResponseEntity<Comment> createComment(@Valid @RequestBody @DTO(CommentCreatableDTO.class) Comment comment) {
        return ResponseEntity.ok(singleRepositoryService.create(comment));
    }

    @PutMapping("comments/{id}")
    public ResponseEntity<Comment> updateComment(@PathVariable Long id, @Valid @RequestBody @DTO(CommentUpdatableDTO.class) Comment comment) {
        return ResponseEntity.ok(singleRepositoryService.update(id, comment));
    }


    @DeleteMapping("comments/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable(value = "id") Long id) {
        singleRepositoryService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/posts/{id}/comments")
    public Page<Comment> getAllCommentsByPostId(@PathVariable(value = "id") Long postId, Pageable pageable) {
        return commentService.getManyByOne(postId, pageable);
    }

    @PostMapping("/posts/{id}/comments")
    public Comment createComment(@PathVariable(value = "id") Long postId, @Valid @RequestBody @DTO(CommentCreatableDTO.class) Comment comment) {
        return commentService.create(postId, comment);
    }

    @PutMapping("/posts/{postId}/comments/{commentId}")
    public Comment updateComment(@PathVariable(value = "postId") Long postId, @PathVariable(value = "commentId") Long commentId, @Valid @RequestBody @DTO(CommentCreatableDTO.class) Comment comment) {
        return commentService.update(postId, commentId, comment);
    }

    @DeleteMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable(value = "postId") Long postId, @PathVariable(value = "commentId") Long commentId) {
        commentService.delete(postId, commentId);
        return ResponseEntity.ok().build();
    }


}