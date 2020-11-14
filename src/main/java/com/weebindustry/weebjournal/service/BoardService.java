package com.weebindustry.weebjournal.service;

import com.weebindustry.weebjournal.dto.BoardDTO;
import com.weebindustry.weebjournal.exception.WeebJournalException;
import com.weebindustry.weebjournal.models.Board;
import com.weebindustry.weebjournal.repositories.BoardRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final AuthService authService;

    @Transactional(readOnly = true)
    public List<BoardDTO> getAll() {
        return boardRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    private BoardDTO save(BoardDTO boardDTO) {
        Board board = boardRepository.save(mapToBoard(boardDTO));
        boardDTO.setId(board.getBoardId());
        return boardDTO;
    }

    @Transactional(readOnly = true)
    private BoardDTO getBoard(Long id) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new WeebJournalException("Board not found with id - " + id));
        return mapToDTO(board);
    }

    private BoardDTO mapToDTO(Board board) {
        return BoardDTO
                .builder()
                .name(board.getName())
                .id(board.getBoardId())
                .postCount(board.getPosts().size())
                .build();
    }

    private Board mapToBoard(BoardDTO boardDTO) {
        return Board
                .builder()
                .name("/b/" + boardDTO.getName())
                .description(boardDTO.getDescription())
                .user(authService.getCurrentUser())
                .createdDate(Instant.now())
                .build();
    }
}
