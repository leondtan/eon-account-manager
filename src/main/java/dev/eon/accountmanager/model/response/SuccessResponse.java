package dev.eon.accountmanager.model.response;

import dev.eon.accountmanager.feedback.SuccessCode;
import dev.eon.accountmanager.util.ValueUtil;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class SuccessResponse extends BaseResponse {

    private Object data;

    public SuccessResponse(Object data, HttpStatus code) {
        super();
        this.data = data;
        this.message = "";
        this.code = code.value();
        this.success = true;
    }

    public SuccessResponse(Object data, HttpStatus code, String message) {
        super();
        this.data = data;
        this.message = message;
        this.code = code.value();
        this.success = true;
    }

    public SuccessResponse(SuccessCode successCode, HttpStatus code) {
        super();
        this.data = successCode.getDescription();
        this.message = successCode.toString();
        this.code = code.value();
        this.success = true;
    }

    public String toJson() {
        return ValueUtil.gson.toJson(this);
    }
}
