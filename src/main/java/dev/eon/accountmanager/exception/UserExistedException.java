package dev.eon.accountmanager.exception;

import lombok.Data;

@Data
public class UserExistedException extends Exception{

    private String identifier;

    private String value;

    public UserExistedException(String identifier, String value) {
        super(String.format("User with %s: %s already exist", identifier, value));
    }

}
