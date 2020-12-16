package com.weebindustry.weebjournal.service;

import java.util.Optional;

import com.weebindustry.weebjournal.dto.VoteDTO;
import com.weebindustry.weebjournal.exception.PostNotFoundException;
import com.weebindustry.weebjournal.exception.WeebJournalException;
import com.weebindustry.weebjournal.models.Post;
import com.weebindustry.weebjournal.models.Vote;
import com.weebindustry.weebjournal.models.enums.VoteType;
import com.weebindustry.weebjournal.repositories.PostRepository;
import com.weebindustry.weebjournal.repositories.VoteRepository;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class VoteService {
    private final VoteRepository voteRepository;
    private final PostRepository postRepository;
    private final AuthService authService;

    public void vote(VoteDTO voteDTO) {
        Post post = postRepository.findById(voteDTO.getPostId())
            .orElseThrow(() -> new PostNotFoundException("Post Not Found with ID - " + voteDTO.getPostId()));
        Optional<Vote> voteByPostAndUser = voteRepository.findTopByPostAndUserOrderByVoteIdDesc(post, authService.getCurrentUser());
        if (voteByPostAndUser.isPresent() && voteByPostAndUser.get().getVoteType().equals(voteDTO.getVoteType())) {
            throw new WeebJournalException("You have already " + voteDTO.getVoteType() + "'d for this post");
        }

        if (VoteType.UPVOTE.equals(voteDTO.getVoteType())) {
            post.setVoteCount(post.getVoteCount() + 1);
        }
        else {
            post.setVoteCount(post.getVoteCount() - 1);
        }

        voteRepository.save(mapToVote(voteDTO, post));
        postRepository.save(post);
        
    }

    private Vote mapToVote(VoteDTO voteDTO, Post post) {
        return Vote.builder()
            .voteType(voteDTO.getVoteType())
            .post(post)
            .user(authService.getCurrentUser())
            .build();
    }
    
}
