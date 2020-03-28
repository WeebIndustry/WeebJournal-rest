package com.weebindustry.weebjournal.payload.post;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PostRequest {
    private String title;

    private String content;
}
