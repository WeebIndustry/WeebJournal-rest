package com.weebindustry.weebjournal.payload.users;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;
import java.util.Date;

@Data
@AllArgsConstructor
public class UserProfile {

    private Long id;

    private String username;

    private String displayname;

    private Instant joinedDate;

    private Date dateOfBirth;

    private Long postCount;

}
