package dev.eon.accountmanager.controller;

import dev.eon.accountmanager.feedback.ErrorCode;
import dev.eon.accountmanager.model.response.ErrorResponse;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@RestController
public class AppErrorController implements ErrorController {

    @RequestMapping("/error")
    public ErrorResponse handleError(HttpServletRequest request, Exception exc) {
        HttpStatus status = HttpStatus.valueOf(Integer.parseInt(request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE).toString()));
        ErrorResponse error = null;
        switch (status.value()) {
            case 400:
                error = new ErrorResponse(ErrorCode.REQUEST_DATA_INCORRECT, status);
                break;

            case 401:
                error = new ErrorResponse(ErrorCode.AUTH_INCORRECT, status);
                break;

            case 404:
                error = new ErrorResponse(ErrorCode.API_NOT_EXIST, status);
                break;

            case 405:
                error = new ErrorResponse(ErrorCode.METHOD_INCORRECT, status);
                break;

            case 500:
                error = new ErrorResponse(exc.getMessage(), status, ErrorCode.SERVER_ERROR.toString());
                break;

            default:
                exc.printStackTrace();
                error = new ErrorResponse(ErrorCode.UNKNOWN_ERROR, status);
                break;
        }

        return error;
    }

}