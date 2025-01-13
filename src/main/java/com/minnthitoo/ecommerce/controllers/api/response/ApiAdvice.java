package com.emkvswwm.ecotrendz.api.response;

import com.emkvswwm.ecotrendz.api.exceptions.errorcodes.ErrorCodes;
import com.emkvswwm.ecotrendz.model.dto.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

}
