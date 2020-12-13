package com.weebindustry.weebjournal.controllers;

import java.util.List;

import com.weebindustry.weebjournal.dto.BoardDTO;
import com.weebindustry.weebjournal.service.BoardService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/board")
@AllArgsConstructor
@Slf4j
public class BoardController {

    private final BoardService boardService;

    
    @PostMapping
    public ResponseEntity<BoardDTO> createBoard(@RequestBody BoardDTO boardDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(boardService.save(boardDTO));
    }

    @GetMapping
    public ResponseEntity<List<BoardDTO>> getAllBoards() {
        return ResponseEntity.status(HttpStatus.OK).body(boardService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BoardDTO> getBoard(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(boardService.getBoard(id));
    }
    
}