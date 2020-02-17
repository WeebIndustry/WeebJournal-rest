package com.weebindustry.weebjournal.dtos.users;

import lombok.Data;

import java.util.*;

@Data
public class UserCreatableDTO {
    
    private String username;

    private String password;

    private String email;

    private String biography;

    private String displayname;

    private Date dateOfBirth;
    
}