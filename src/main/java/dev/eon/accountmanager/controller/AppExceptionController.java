package dev.eon.accountmanager.controller;

import dev.eon.accountmanager.exception.GeneralException;
import dev.eon.accountmanager.exception.ItemNotFoundException;
import dev.eon.accountmanager.feedback.ErrorCode;
import dev.eon.accountmanager.model.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class AppExceptionController {

    // #VALIDATION
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<org.springframework.validation.FieldError> errors = result.getFieldErrors();
        List<String> errorList = new ArrayList<>();
        for (org.springframework.validation.FieldError error : errors) {
            errorList.add(String.format(
                    "Invalid Request Body %s: '%s' - %s",
                    error.getField(),
                    error.getRejectedValue(),
                    error.getDefaultMessage()
            ));
        }

        return new ErrorResponse(ErrorCode.REQUEST_BODY_VALIDATION, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public ErrorResponse handleBindException(BindException ex) {
        BindingResult result = ex.getBindingResult();
        List<org.springframework.validation.FieldError> errors = result.getFieldErrors();
        List<String> errorList = new ArrayList<>();
        for (org.springframework.validation.FieldError error : errors) {
            errorList.add(String.format(
                    "Invalid Form Data %s: '%s' - %s",
                    error.getField(),
                    error.getRejectedValue(),
                    error.getDefaultMessage()
            ));
        }

        return new ErrorResponse(errorList, HttpStatus.BAD_REQUEST, ErrorCode.FORM_DATA_VALIDATION.toString());
    }

    // #NO-REQUEST-BODY
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ErrorResponse handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        return new ErrorResponse(ErrorCode.REQUEST_BODY_NOT_EXIST, HttpStatus.BAD_REQUEST);
    }

    // #AUTHENTICATION-BAD-REQUEST
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadCredentialsException.class)
    public ErrorResponse handleBadCredentialsException(BadCredentialsException ex) {
        return new ErrorResponse(ErrorCode.AUTH_INCORRECT, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ItemNotFoundException.class)
    public ErrorResponse handleItemNotFoundException(ItemNotFoundException ex) {
        return new ErrorResponse(
                ex.getMessage(),
                HttpStatus.BAD_REQUEST,
                ErrorCode.ITEM_NOT_EXIST.toString()
        );

    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(GeneralException.class)
    public ErrorResponse handleBadCredentialsException(GeneralException ex) {
        if (ex.getErrorCode() != null) {
            return new ErrorResponse(ex.getErrorCode(), HttpStatus.BAD_REQUEST);
        } else {
            return new ErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST, ErrorCode.GENERAL.toString());
        }
    }

}