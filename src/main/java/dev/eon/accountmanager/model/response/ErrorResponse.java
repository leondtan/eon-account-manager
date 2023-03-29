package dev.eon.accountmanager.model.response;

import dev.eon.accountmanager.feedback.ErrorCode;
import dev.eon.accountmanager.util.ValueUtil;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ErrorResponse extends BaseResponse {

    private Object error;

    public ErrorResponse(Object error, HttpStatus code) {
        super();
        this.error = error;
        this.message = "";
        this.code = code.value();
        this.success = false;
    }

    public ErrorResponse(Object error, HttpStatus code, String message) {
        super();
        this.error = error;
        this.message = message;
        this.code = code.value();
        this.success = false;
    }

    public ErrorResponse(ErrorCode errorCode, HttpStatus code) {
        super();
        this.error = errorCode.getDescription();
        this.message = errorCode.toString();
        this.code = code.value();
        this.success = false;
    }

    public String toJson() {
        return ValueUtil.gson.toJson(this);
    }
}
