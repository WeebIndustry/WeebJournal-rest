package com.weebindustry.weebjournal.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.weebindustry.weebjournal.dtos.UserLoginDTO;
import com.weebindustry.weebjournal.exceptions.ResourceNotFoundException;
import com.weebindustry.weebjournal.models.User;
import com.weebindustry.weebjournal.repositories.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.*;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/users")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserRepository repo;

    @GetMapping("/")
    public ResponseEntity<List<User>> findAll() {
        return ResponseEntity.ok(repo.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable(value = "id") Long id) {

        Optional<User> result = repo.findById(id);

        if (!result.isPresent()) {
            log.error("Id {} is not existed", id);
            ResponseEntity.badRequest().build();
        }

        

        return ResponseEntity.ok(result.get());
    }

    @PostMapping("/")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        return ResponseEntity.ok(repo.save(user));
    }

    @PostMapping("/login")
    public User loginValidation(@Valid @RequestBody UserLoginDTO dto) {
        Optional<User> result = repo.loginValidation(dto.getUsername(), dto.getPassword());

        
        
        return result.orElse(null);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable Long id, @Valid @RequestBody User user) {
        if (!repo.findById(id).isPresent()) {
            log.error("Id {} is not existed", id);
            ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(repo.save(user));
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable(value = "id") Long id) {
       if (!repo.findById(id).isPresent()) {
           log.error("ID {} is not existed!", "id");
           ResponseEntity.badRequest().build();
       }

        repo.deleteById(id);

        return ResponseEntity.ok().build();
    }
}