package com.weebindustry.weebjournal.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
@Data
@EqualsAndHashCode(callSuper=false)
public class ParentEntityNotFound extends RuntimeException {
    private Class<?> parentEntity;

    public ParentEntityNotFound(Class<?> parentEntity) {
        super("Parent Entity " + parentEntity.getName() + " not found to execute SQL query for the request");
    }
}
