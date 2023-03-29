package dev.eon.accountmanager.exception;

import java.util.UUID;

public class ItemNotFoundException extends Exception {

    private String objectType;

    private UUID id;

    public ItemNotFoundException(Class className, UUID id) {
        super(String.format("Item %s: %s not found", className.getSimpleName(), id.toString()));

        objectType = className.getSimpleName();
        this.id = id;
    }

}
