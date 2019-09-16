package com.lesson5.Exception;

public class ThereIsNoSuchItemException extends Exception {

    public ThereIsNoSuchItemException() {
        super();
    }

    public ThereIsNoSuchItemException(String message) {
        super(message);
    }

    public ThereIsNoSuchItemException(String message, Throwable cause) {
        super(message, cause);
    }

    public ThereIsNoSuchItemException(Throwable cause) {
        super(cause);
    }

    protected ThereIsNoSuchItemException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
