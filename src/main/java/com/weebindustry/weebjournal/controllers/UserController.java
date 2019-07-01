package com.weebindustry.weebjournal.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.weebindustry.weebjournal.exceptions.ResourceNotFoundException;
import com.weebindustry.weebjournal.models.User;
import com.weebindustry.weebjournal.repositories.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/users")
public class UserController {


    private final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserRepository repo;

    @GetMapping("/")
    public List<User> findAll() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public User findById(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {

        Optional<User> result = repo.findById(id);

        if (!result.isPresent()) {
            throw new ResourceNotFoundException("User", "id", id);
        }

        return result.orElse(null);
    }

    @PostMapping("/")
    public User createUser(@Valid @RequestBody User user) {
        return repo.save(user);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable(value = "id") Long id, @Valid @RequestBody User user) {
        Optional<User> result = repo.findById(id);
        
        if (!result.isPresent()) {
            throw new ResourceNotFoundException("User", "id", id);
        }
        result.orElse(null).setUsername(user.getUsername());
        result.orElse(null).setPassword(user.getPassword());
        result.orElse(null).setEmail(user.getEmail());
        result.orElse(null).setBiography(user.getBiography());
        result.orElse(null).setJoinedDate(user.getJoinedDate());
        result.orElse(null).setDateOfBirth(user.getDateOfBirth());
        result.orElse(null).setDisplayname(user.getDisplayname());
        
        User updatedUser = repo.save(result.orElse(null));
        return updatedUser;
    }

    @DeleteMapping("/notes/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable(value = "id") Long id) {
        User user = repo.findById(id).orElse(null);
        
        repo.delete(user);

        return ResponseEntity.ok().build();
    }
}