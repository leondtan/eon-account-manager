package dev.eon.accountmanager.exception;

import java.time.format.DateTimeFormatter;

public class IncorrectTimeFormatException extends Exception {

    private String value;
    private DateTimeFormatter format;

    public IncorrectTimeFormatException(String value, DateTimeFormatter format) {
        super(String.format("%s didn't match %s format", value, format.toString()));

        this.value = value;
        this.format = format;
    }

    public String getValue() {
        return value;
    }

    public DateTimeFormatter getFormat() {
        return format;
    }
}
