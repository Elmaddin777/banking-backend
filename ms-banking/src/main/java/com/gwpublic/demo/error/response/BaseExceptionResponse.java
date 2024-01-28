package com.gwpublic.demo.error.response;

import lombok.Data;

@Data
public class BaseExceptionResponse {

    private String code;
    private int status;
    private String path;
    private String message;

}
