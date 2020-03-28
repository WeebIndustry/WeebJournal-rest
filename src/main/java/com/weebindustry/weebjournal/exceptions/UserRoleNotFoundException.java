package com.weebindustry.weebjournal.exceptions;

import com.weebindustry.weebjournal.models.RoleName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
@Data
@EqualsAndHashCode(callSuper=false)
public class UserRoleNotFoundException extends RuntimeException {

    public UserRoleNotFoundException(RoleName roleName) {
        super("User Role " + roleName.name() + " not found");
    }
}
