package com.weebindustry.weebjournal.controllers;

import com.weebindustry.weebjournal.dtos.comments.CommentCreatableDTO;
import com.weebindustry.weebjournal.models.Comment;
import com.weebindustry.weebjournal.util.DTO;
import com.weebindustry.weebjournal.util.HelperServiceOneToMany;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class CommentController {

    @Autowired
    private HelperServiceOneToMany<Comment> commentService;

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