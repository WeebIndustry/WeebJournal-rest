package com.weebindustry.weebjournal.service;

import java.util.List;
import java.util.stream.Collectors;

import com.weebindustry.weebjournal.dto.CommentDTO;
import com.weebindustry.weebjournal.exception.PostNotFoundException;
import com.weebindustry.weebjournal.exception.UsernameNotFoundException;
import com.weebindustry.weebjournal.mapper.CommentMapper;
import com.weebindustry.weebjournal.models.Comment;
import com.weebindustry.weebjournal.models.NotificationEmail;
import com.weebindustry.weebjournal.models.Post;
import com.weebindustry.weebjournal.models.User;
import com.weebindustry.weebjournal.repositories.CommentRepository;
import com.weebindustry.weebjournal.repositories.PostRepository;
import com.weebindustry.weebjournal.repositories.UserRepository;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CommentService {
    private static final String POST_URL = "";
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final AuthService authService;
    private final CommentMapper commentMapper;
    private final CommentRepository commentRepository;
    private final MailContentBuilder mailContentBuilder;
    private final MailService mailService;
    
    public void save(CommentDTO commentDTO) {
        Post post = postRepository.findById(commentDTO.getPostId()).orElseThrow(() -> new PostNotFoundException(commentDTO.getPostId().toString()));
        Comment comment = commentMapper.map(commentDTO,post, authService.getCurrentUser());
        commentRepository.save(comment);
        
        String message = mailContentBuilder.build(authService.getCurrentUser() + "posted a comment on your post." + POST_URL);
        sendCommentNotification(message, post.getUser());
    }

    private void sendCommentNotification(String message, User user) {
        mailService.sendMail(new NotificationEmail(user.getUsername() + " Commented on your post", user.getEmail(), message));
    }

    public List<CommentDTO> getAllCommentsForPost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException(postId.toString()));
        return commentRepository.findByPost(post)
            .stream()
            .map(commentMapper::mapToDto).collect(Collectors.toList()); 
    }

    public List<CommentDTO> getAllCommentsForUser(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
        return commentRepository.findAllByUser(user)
            .stream()
            .map(commentMapper::mapToDto)
            .collect(Collectors.toList());
    }
}
