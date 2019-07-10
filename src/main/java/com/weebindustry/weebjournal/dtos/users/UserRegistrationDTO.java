package com.weebindustry.weebjournal.dtos.users;

import lombok.Data;

import java.util.Date;

import javax.validation.constraints.*;

@Data
public class UserRegistrationDTO {


    @NotNull
    @NotBlank
    private String username;

    @NotNull
    @NotBlank
    private String password;

    @NotNull
    @NotBlank
    private String email;

    private String biography;

    @NotNull
    @NotBlank
    private String displayname;
    
    private Date dateOfBirth;
}