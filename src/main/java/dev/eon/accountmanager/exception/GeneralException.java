package dev.eon.accountmanager.exception;

import dev.eon.accountmanager.feedback.ErrorCode;
import lombok.Data;

@Data
public class GeneralException extends Exception {
    private ErrorCode errorCode;

    public GeneralException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public GeneralException(String message) {
        super(message);
    }
}
