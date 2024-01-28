package com.gwpublic.demo.error;

import com.gwpublic.demo.error.response.BaseExceptionResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class BaseException extends RuntimeException {

    private BaseExceptionResponse response;
    private String uuid;

}
