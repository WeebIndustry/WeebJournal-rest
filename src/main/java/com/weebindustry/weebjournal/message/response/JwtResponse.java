package com.weebindustry.weebjournal.message.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class JwtResponse {

    @NonNull
    private String token;
    private String type = "Bearer";
}
