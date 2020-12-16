package com.weebindustry.weebjournal.exception;

public class UsernameNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1617176803795545994L;

    public UsernameNotFoundException(String message) {
        super(message);
    }
    
}
