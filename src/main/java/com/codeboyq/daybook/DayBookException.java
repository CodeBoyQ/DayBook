package com.codeboyq.daybook;

public class DayBookException extends Exception {

    private static final long serialVersionUID = -4353093866899178794L;

    public DayBookException(String message) {
        super (message);
    }

    public DayBookException(Throwable cause) {
        super (cause);
    }

    public DayBookException(String message, Throwable cause) {
        super (message, cause);
    }

}