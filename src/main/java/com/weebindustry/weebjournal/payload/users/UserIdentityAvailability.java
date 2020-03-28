package com.weebindustry.weebjournal.payload.users;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserIdentityAvailability {
    private Boolean isAvailable;
}
