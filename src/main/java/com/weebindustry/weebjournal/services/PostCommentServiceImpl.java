package com.weebindustry.weebjournal.services;

import com.weebindustry.weebjournal.exceptions.ResourceNotFoundException;
import com.weebindustry.weebjournal.models.Comment;
import com.weebindustry.weebjournal.repositories.CommentRepository;
import com.weebindustry.weebjournal.repositories.PostRepository;
import com.weebindustry.weebjournal.util.HelperServiceOneToMany;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service("PostCommentService")
public class PostCommentServiceImpl implements HelperServiceOneToMany<Comment> {

    private final PostRepository postRepo;

    private final CommentRepository commentRepo;

    public PostCommentServiceImpl(PostRepository postRepo, CommentRepository commentRepo) {
        this.postRepo = postRepo;
        this.commentRepo = commentRepo;
    }

    @Override
    public Page<Comment> getManyByOne(Long id, Pageable pageable) {
        return commentRepo.findByPostId(id, pageable);
    }

    @Override
    public Comment create(Long id, Comment type) {
        return postRepo.findById(id).map(post -> {
            type.setPost(post);
            return commentRepo.save(type);
        }).orElseThrow(() -> new ResourceNotFoundException("Post Id " + id + " not found"));
    }

    @Override
    public Comment update(Long idOne, Long idMany, Comment type) {
        if (!postRepo.findById(idOne).isPresent()) {
            ResponseEntity.badRequest().build();
            throw new ResourceNotFoundException("Post Id " + idOne + " not found");
        }
        return commentRepo.findById(idMany).map(comment -> {
            return commentRepo.save(comment);
        }).orElseThrow(() -> new ResourceNotFoundException("Comment Id " + idMany + " not found"));
    }

    @Override
    public void delete(Long idOne, Long idMany) {
        commentRepo.findByIdAndPostId(idMany, idOne).map(comment -> {
            commentRepo.delete(comment);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Comment not found with id " + idMany + " and postId " + idOne));
    }
}
