package com.weebindustry.weebjournal.dtos.users;

import lombok.Data;

import java.util.*;

@Data
public class UserReadableDTO {

    private Long id;

    private String username;

    private Date joinedDate;

    private String email;

    private String biography;

    private String displayname;

    private Date dateOfBirth;
}