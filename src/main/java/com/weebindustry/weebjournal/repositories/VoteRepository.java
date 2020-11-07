package com.weebindustry.weebjournal.repositories;

import com.weebindustry.weebjournal.models.Post;
import com.weebindustry.weebjournal.models.User;
import com.weebindustry.weebjournal.models.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VoteRepository extends JpaRepository<Vote, Long> {
    Optional<Vote> findTopByPostAndUserOrderByVoteIdDesc(Post post, User currentUser);

}
