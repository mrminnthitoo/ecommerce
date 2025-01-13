package com.minnthitoo.ecommerce.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RestResponse {

    String message;
    String errorCode;
    Object error;
    Object data;

}
