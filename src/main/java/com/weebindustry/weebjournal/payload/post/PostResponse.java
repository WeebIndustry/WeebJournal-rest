package com.weebindustry.weebjournal.payload.post;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.weebindustry.weebjournal.payload.users.UserSummary;
import lombok.Data;

import java.util.Date;

@Data
public class PostResponse {
    private Long id;
    private String title;
    private String content;
    private UserSummary createdBy;
    private Date dateCreated;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long commentCount;
    private Long likeCount;
}
