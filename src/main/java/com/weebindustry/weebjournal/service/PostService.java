package com.weebindustry.weebjournal.service;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import com.weebindustry.weebjournal.dto.PostRequest;
import com.weebindustry.weebjournal.dto.PostResponse;
import com.weebindustry.weebjournal.exception.BoardNotFoundException;
import com.weebindustry.weebjournal.exception.PostNotFoundException;
import com.weebindustry.weebjournal.exception.UsernameNotFoundException;
import com.weebindustry.weebjournal.exception.WeebJournalException;
import com.weebindustry.weebjournal.mapper.PostMapper;
import com.weebindustry.weebjournal.models.Board;
import com.weebindustry.weebjournal.models.Post;
import com.weebindustry.weebjournal.models.User;
import com.weebindustry.weebjournal.repositories.BoardRepository;
import com.weebindustry.weebjournal.repositories.PostRepository;
import com.weebindustry.weebjournal.repositories.UserRepository;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Transactional
public class PostService {
    private final PostRepository postRepository;
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    private final AuthService authService;
    private final PostMapper postMapper;

    public void save(PostRequest postRequest) {
        Board board = boardRepository.findByName(postRequest.getBoardName())
                .orElseThrow(() -> new WeebJournalException(postRequest.getBoardName()));

        postRepository.save(postMapper.map(postRequest, board, authService.getCurrentUser()));
    }

    @Transactional(readOnly = true)
    public PostResponse getPost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new PostNotFoundException(id.toString()));
        return postMapper.mapToDto(post);
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getAllPosts() {
        return postRepository.findAll().stream().map(postMapper::mapToDto).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getPostsByBoard(Long boardId) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new BoardNotFoundException(boardId.toString()));
        return postRepository.findAllByBoard(board).stream().map(postMapper::mapToDto).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getPostsByUsername(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
        return postRepository.findByUser(user).stream().map(postMapper::mapToDto).collect(Collectors.toList());
    }

}