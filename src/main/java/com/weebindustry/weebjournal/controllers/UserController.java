package com.weebindustry.weebjournal.controllers;

import javax.validation.Valid;


import com.weebindustry.weebjournal.exceptions.RequestParamValueNotDefinedException;
import com.weebindustry.weebjournal.exceptions.ResourceNotFoundException;
import com.weebindustry.weebjournal.models.User;
import com.weebindustry.weebjournal.payload.users.UserIdentityAvailability;
import com.weebindustry.weebjournal.payload.users.UserProfile;
import com.weebindustry.weebjournal.repositories.CommentRepository;
import com.weebindustry.weebjournal.repositories.PostRepository;
import com.weebindustry.weebjournal.repositories.UserRepository;
import com.weebindustry.weebjournal.util.DTO;
import com.weebindustry.weebjournal.util.HelperService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/users")
@Api(value = "User Management API")
public class UserController {


    private final UserRepository userRepository;

    private final PostRepository postRepository;

    private final CommentRepository commentRepository;

    @Autowired
    public UserController(UserRepository userRepository, PostRepository postRepository, CommentRepository commentRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }


    @ApiOperation(value = "View a list of available users", response = List.class)
    @GetMapping("")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAllUsers(Pageable pageable, @RequestParam(required = false, defaultValue = "false") String hasPageable) {
        if (hasPageable.equals("true")) {
            return ResponseEntity.ok(userRepository.findAll(pageable));
        }
        else if(hasPageable.equals("false")) {
            return ResponseEntity.ok(userRepository.findAll());
        }
        else {
            throw new RequestParamValueNotDefinedException("hasPageable", hasPageable);
        }

    }

    @ApiOperation(value = "Get an User by Id")
    @GetMapping("/{id}")
    public ResponseEntity<UserProfile> findById(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));

        long postCount = postRepository.countByUserId(id);

        UserProfile userProfile = new UserProfile(user.getId(), user.getUsername(), user.getDisplayname(), user.getCreatedAt(), user.getDateOfBirth(), postCount);

        return ResponseEntity.ok(userProfile);
    }

    @ApiOperation(value = "Get an User by Username")
    @GetMapping("/{username}")
    public ResponseEntity<UserProfile> findByUserName(@PathVariable(value = "username") String username) throws ResourceNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException("User not found with username " + username));

        long postCount = postRepository.countByUserName(username);

        UserProfile userProfile = new UserProfile(user.getId(), user.getUsername(), user.getDisplayname(), user.getCreatedAt(), user.getDateOfBirth(), postCount);

        return ResponseEntity.ok(userProfile);
    }


    @ApiOperation(value = "Count all users")
    @GetMapping("/count")
    public ResponseEntity<Long> countUsers() {
        return ResponseEntity.ok(userRepository.countUsers());
    }

    @GetMapping("/checkUsernameAvailability")
    public ResponseEntity<UserIdentityAvailability> checkUsernameAvailability(@RequestParam(value = "username") String username) {
        Boolean isAvailable = !userRepository.existsByUsername(username);
        return ResponseEntity.ok(new UserIdentityAvailability(isAvailable));
    }

    @GetMapping("/checkEmailAvailability")
    public ResponseEntity<UserIdentityAvailability> checkEmailAvailability(@RequestParam(value = "email") String email) {
        Boolean isAvailable = !userRepository.existsByEmail(email);
        return ResponseEntity.ok(new UserIdentityAvailability(isAvailable));
    }

}