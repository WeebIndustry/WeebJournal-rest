package com.weebindustry.weebjournal.controllers;

import com.weebindustry.weebjournal.dto.VoteDTO;
import com.weebindustry.weebjournal.service.VoteService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/votes")
@AllArgsConstructor
public class VoteController {

    private final VoteService voteService;

    @PostMapping
    public ResponseEntity<Void> vote(@RequestBody VoteDTO voteDTO) {
        voteService.vote(voteDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
