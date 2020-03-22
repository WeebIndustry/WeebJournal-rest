package com.weebindustry.weebjournal.services;

import com.weebindustry.weebjournal.exceptions.ResourceNotFoundException;
import com.weebindustry.weebjournal.models.Post;
import com.weebindustry.weebjournal.models.User;
import com.weebindustry.weebjournal.repositories.PostRepository;
import com.weebindustry.weebjournal.repositories.UserRepository;
import com.weebindustry.weebjournal.util.HelperService;
import com.weebindustry.weebjournal.util.HelperServiceOneToMany;

import lombok.experimental.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("PostService")
public class PostServiceImpl implements HelperService<Post>, HelperServiceOneToMany<Post> {

    @Autowired
    private PostRepository postRepo;

    @Autowired
    private UserRepository userRepo;

    @Override
    public List<Post> list() {
        return postRepo.findAll();
    }

    @Override
    public Page<Post> findAll(Pageable pageable) {
        return postRepo.findAll(pageable);
    }

    @Override
    public Post findById(Long id) {
        Optional<Post> result = postRepo.findById(id);

        if (!result.isPresent()) {

            ResponseEntity.badRequest().build();
            throw new ResourceNotFoundException("User not found :: " + id);
        }
        return result.get();
    }

    @Override
    public Post create(Post type) {
        return postRepo.save(type);
    }

    @Override
    public Post update(Long id, Post type) {
        Optional<Post> result = postRepo.findById(id);

        if (!result.isPresent()) {

            ResponseEntity.badRequest().build();
            throw new ResourceNotFoundException("User not found :: " + id);
        }
        return postRepo.save(type);
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