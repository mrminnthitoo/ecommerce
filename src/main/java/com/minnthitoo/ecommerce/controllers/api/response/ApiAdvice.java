package com.minnthitoo.ecommerce.controllers.api.response;

import com.minnthitoo.ecommerce.controllers.api.errorcodes.ErrorCodes;
import com.minnthitoo.ecommerce.models.dto.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Objects;

@RestControllerAdvice
public class ApiAdvice {

    @Autowired
    private ApiResponse apiResponse;

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<RestResponse> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException exception){
        String error = exception.getName() + " should be type of " + Objects.requireNonNull(exception.getRequiredType()).getName();
        return this.apiResponse.errorResponse(HttpStatus.BAD_REQUEST,
                "Invalid Type.",
                ErrorCodes.TYPE_ERROR,
                error);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<RestResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException exception){
        String error = exception.getMessage();
        return this.apiResponse.errorResponse(HttpStatus.BAD_REQUEST,
                "request body must be json format.",
                ErrorCodes.FORMAT_ERROR,
                error);
    }

}
