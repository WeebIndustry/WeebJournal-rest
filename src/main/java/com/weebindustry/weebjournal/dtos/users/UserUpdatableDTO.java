package com.weebindustry.weebjournal.dtos.users;

import lombok.Data;

import java.util.*;

@Data
public class UserUpdatableDTO {

    // ADMIN PRIVILEGES ONLY CAN MODIFY THESE
    private String username;

    private String password;

    private String email;

    // BUT THOSE BELOW ARE NOT (Available on user's preference)

    private String biography;

    private String displayname;

    private Date dateOfBirth;
    
}