package com.weebindustry.weebjournal.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
@Data
@EqualsAndHashCode(callSuper=false)
public class RequestParamValueNotDefinedException extends RuntimeException {

    private String requestParam;
    private String paramValue;

    public RequestParamValueNotDefinedException(String requestParam, String paramValue) {
        super(String.format("Parameter Value: '%s' not defined in Request Parameter '%s'", paramValue, requestParam));
    }
}
