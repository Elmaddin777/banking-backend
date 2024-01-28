package com.gwpublic.demo.error.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponse {

    private ErrorCode code;
    private String message;
    private String uuid;

}
