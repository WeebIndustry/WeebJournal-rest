package com.weebindustry.weebjournal.controllers;

import com.weebindustry.weebjournal.repositories.CommentRepository;
import com.weebindustry.weebjournal.repositories.PostRepository;
import com.weebindustry.weebjournal.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/posts/")
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    
}
