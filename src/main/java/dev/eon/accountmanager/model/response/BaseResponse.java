package dev.eon.accountmanager.model.response;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.util.Date;

@Data
public class BaseResponse {

    protected int code;
    protected String message;
    protected boolean success = true;

    @Setter(AccessLevel.NONE)
    protected Date timestamp;

    public BaseResponse() {
        this.timestamp = new Date();
    }
}
