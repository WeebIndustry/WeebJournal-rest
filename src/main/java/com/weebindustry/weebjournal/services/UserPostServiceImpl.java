package com.weebindustry.weebjournal.services;

import com.weebindustry.weebjournal.exceptions.ResourceNotFoundException;
import com.weebindustry.weebjournal.models.Post;
import com.weebindustry.weebjournal.repositories.PostRepository;
import com.weebindustry.weebjournal.repositories.UserRepository;
import com.weebindustry.weebjournal.util.HelperServiceOneToMany;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service("UserPostService")
public class UserPostServiceImpl implements HelperServiceOneToMany<Post> {

    private final UserRepository userRepo;

    private final PostRepository postRepo;

    public UserPostServiceImpl(UserRepository userRepo, PostRepository postRepo) {
        this.userRepo = userRepo;
        this.postRepo = postRepo;
    }

    @Override
    public Page<Post> getManyByOne(Long id, Pageable pageable) {
        return postRepo.findByUserId(id, pageable);
    }

    @Override
    public Post create(Long id, Post type) {
        return userRepo.findById(id).map(user -> {
            type.setUser(user);
            return postRepo.save(type);
        }).orElseThrow(() -> new ResourceNotFoundException("UserId " + id + " not found"));
    }

    @Override
    public Post update(Long idOne, Long idMany, Post type) {
        if (!userRepo.findById(idOne).isPresent()) {
            ResponseEntity.badRequest().build();
            throw new ResourceNotFoundException("User Id " + idOne + " not found");
        }

        return postRepo.findById(idMany).map(post -> {
            return postRepo.save(post);
        }).orElseThrow(() -> new ResourceNotFoundException("Post Id " + idMany + " not found"));
    }

    @Override
    public void delete(Long idOne, Long idMany) {
        postRepo.findByIdAndUserId(idMany, idOne).map(post -> {
            postRepo.delete(post);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Post Id " + idMany + " not found"));
    }
}
