package com.weebindustry.weebjournal.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.weebindustry.weebjournal.dtos.users.UserCreatableDTO;
import com.weebindustry.weebjournal.dtos.users.UserUpdatableDTO;
import com.weebindustry.weebjournal.exceptions.ResourceNotFoundException;
import com.weebindustry.weebjournal.models.User;
import com.weebindustry.weebjournal.repositories.UserRepository;
import com.weebindustry.weebjournal.util.DTO;
import com.weebindustry.weebjournal.util.HelperService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private HelperService<User> service;

    @GetMapping("/")
    public ResponseEntity<Page<User>> getAllUsers(Pageable pageable) {
        return ResponseEntity.ok(service.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        return ResponseEntity.ok(service.findById(id));
    }


    @PostMapping("/")
    public ResponseEntity<User> createUser(@Valid @RequestBody @DTO(UserCreatableDTO.class) User user) {
        return ResponseEntity.ok(service.create(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @Valid @RequestBody @DTO(UserUpdatableDTO.class) User user) {
        return ResponseEntity.ok(service.update(id, user));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable(value = "id") Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}