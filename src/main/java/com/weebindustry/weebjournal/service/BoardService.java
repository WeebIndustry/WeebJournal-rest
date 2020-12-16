package com.weebindustry.weebjournal.service;

import com.weebindustry.weebjournal.dto.BoardDTO;
import com.weebindustry.weebjournal.exception.WeebJournalException;
import com.weebindustry.weebjournal.mapper.BoardMapper;
import com.weebindustry.weebjournal.models.Board;
import com.weebindustry.weebjournal.repositories.BoardRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final BoardMapper boardMapper;

    @Transactional
    public BoardDTO save(BoardDTO boardDTO) {
        Board save = boardRepository.save(boardMapper.mapDTOToBoard(boardDTO));
        boardDTO.setId(save.getBoardId());
        return boardDTO;
    }

    @Transactional(readOnly = true)
    public List<BoardDTO> getAll() {
        return boardRepository.findAll()
                .stream()
                .map(boardMapper::mapBoardToDTO)
                .collect(Collectors.toList());
    }

    public BoardDTO getBoard(Long id) {
        Board subreddit = boardRepository.findById(id)
                .orElseThrow(() -> new WeebJournalException("No subreddit found with ID - " + id));
        return boardMapper.mapBoardToDTO(subreddit);
    }

    
}
