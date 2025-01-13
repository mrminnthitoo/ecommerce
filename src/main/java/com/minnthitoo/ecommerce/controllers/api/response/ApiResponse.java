package com.emkvswwm.ecotrendz.api.response;

import com.emkvswwm.ecotrendz.api.exceptions.errorcodes.ErrorCodes;
import com.emkvswwm.ecotrendz.model.dto.RestResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ApiResponse {

    public ResponseEntity<RestResponse> successResponse(HttpStatus statusCode, String message, Object data){
        RestResponse restResponse = new RestResponse();
        restResponse.setMessage(message);
        restResponse.setData(data);
        return ResponseEntity.status(statusCode).body(restResponse);
    }

    public ResponseEntity<RestResponse> errorResponse(HttpStatus statusCode, String message, ErrorCodes errorCode, Object error){
        RestResponse restResponse = new RestResponse();
        restResponse.setMessage(message);
        restResponse.setErrorCode(errorCode.toString());
        restResponse.setError(error);
        return ResponseEntity.status(statusCode).body(restResponse);
    }

    //
    public Map<String, String> getFieldErrors(List<FieldError> fieldErrors){
        Map<String, String> errors = new HashMap<>();
        for (FieldError fieldError : fieldErrors){
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return errors;
    }

}
