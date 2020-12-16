package com.weebindustry.weebjournal.exception;

public class PostNotFoundException extends RuntimeException {
    
    private static final long serialVersionUID = -1573313240182816333L;

    public PostNotFoundException(String message) {
        super(message);
    }
    
}
