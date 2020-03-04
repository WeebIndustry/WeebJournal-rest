package com.weebindustry.weebjournal.message.request;

import com.weebindustry.weebjournal.models.Role;
import com.weebindustry.weebjournal.models.RoleName;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
public class UserSignupDTO {

    @NotBlank
    @Size(min = 3, max = 50)
    private String displayname;

    @NotBlank
    @Size(min = 3, max = 50)
    private String username;

    @NotBlank
    @Size(max = 60)
    @Email
    private String email;

    private Set<RoleName> role;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;
}
