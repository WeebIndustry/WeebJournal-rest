package com.weebindustry.weebjournal.dto;

import com.weebindustry.weebjournal.models.enums.VoteType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoteDTO {
    public VoteType voteType;
    private Long postId;
}
