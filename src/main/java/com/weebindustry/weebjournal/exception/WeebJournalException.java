package com.weebindustry.weebjournal.exception;

public class WeebJournalException extends RuntimeException {
    private static final long serialVersionUID = 2490490552919553081L;

    public WeebJournalException(String message) {
        super(message);
    }

    public WeebJournalException(String message, Exception exception) {
        super(message, exception);
    }
}
