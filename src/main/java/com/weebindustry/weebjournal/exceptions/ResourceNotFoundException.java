package com.weebindustry.weebjournal.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.*;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
@Data
@EqualsAndHashCode(callSuper=false)
public class ResourceNotFoundException extends RuntimeException {
    private static final long serialVersionUID = -6564141208065599750L;

    public ResourceNotFoundException(String message) {
        super(message);
    }
}