package com.weebindustry.weebjournal.dto;

import java.time.Instant;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {
    private Long id;
    private Long postId;
    private Instant createdDate;
    private String text;
    private String userName;
}
