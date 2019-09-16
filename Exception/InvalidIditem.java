package com.lesson5.Exception;

public class InvalidIditem extends Exception {
    public InvalidIditem() {
        super();
    }

    public InvalidIditem(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidIditem(Throwable cause) {
        super(cause);
    }

    protected InvalidIditem(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
