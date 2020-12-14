package com.weebindustry.weebjournal.exception;

public class BoardNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 4968823595434173558L;

    public BoardNotFoundException(String message) {
        super(message);
    }
    
}
