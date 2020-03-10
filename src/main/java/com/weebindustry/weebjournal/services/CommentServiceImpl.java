package com.weebindustry.weebjournal.services;

import com.weebindustry.weebjournal.exceptions.ResourceNotFoundException;
import com.weebindustry.weebjournal.models.Comment;
import com.weebindustry.weebjournal.models.Post;
import com.weebindustry.weebjournal.repositories.CommentRepository;
import com.weebindustry.weebjournal.repositories.PostRepository;
import com.weebindustry.weebjournal.util.HelperService;
import com.weebindustry.weebjournal.util.HelperServiceOneToMany;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CommentServiceImpl implements HelperServiceOneToMany<Comment>, HelperService<Comment> {

    @Autowired
    private PostRepository postRepo;

    @Autowired
    private CommentRepository commentRepo;

    @Override
    public List<Comment> list() {
        return commentRepo.findAll();
    }

    @Override
    public Page<Comment> findAll(Pageable pageable) {
        return commentRepo.findAll(pageable);
    }

    @Override
    public Comment findById(Long id) {
        Optional<Comment> result = commentRepo.findById(id);

        if (!result.isPresent()) {

            ResponseEntity.badRequest().build();
            throw new ResourceNotFoundException("User not found :: " + id);
        }
        return result.get();
    }

    @Override
    public Comment create(Comment type) {
        return commentRepo.save(type);
    }

    @Override
    public Comment update(Long id, Comment type) {
        Optional<Comment> result = commentRepo.findById(id);

        if (!result.isPresent()) {

            ResponseEntity.badRequest().build();
            throw new ResourceNotFoundException("User not found :: " + id);
        }
        return commentRepo.save(type);
    }

    @Override
    public void delete(Long id) {
        if (!postRepo.findById(id).isPresent()) {
            ResponseEntity.badRequest().build();
            throw new ResourceNotFoundException("User not found :: " + id);
        }

        postRepo.deleteById(id);
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