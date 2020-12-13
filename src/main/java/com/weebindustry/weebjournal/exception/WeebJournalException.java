package com.weebindustry.weebjournal.exception;

public class WeebJournalException extends RuntimeException {
    public WeebJournalException(String message) {
        super(message);
    }

    public WeebJournalException(String message, Exception exception) {
        super(message, exception);
    }
}
