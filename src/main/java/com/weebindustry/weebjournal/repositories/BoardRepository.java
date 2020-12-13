package com.weebindustry.weebjournal.repositories;


import com.weebindustry.weebjournal.models.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {
    Optional<Board> findByName(String boardName);
}
