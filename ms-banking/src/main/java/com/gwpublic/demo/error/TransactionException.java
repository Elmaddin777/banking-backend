package com.gwpublic.demo.error;

import com.gwpublic.demo.error.response.BaseExceptionResponse;
import com.gwpublic.demo.error.response.ErrorCode;
import feign.error.FeignExceptionConstructor;
import java.util.UUID;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

@EqualsAndHashCode(callSuper = false)
@Slf4j
@Data
public class TransactionException extends BaseException {

    @FeignExceptionConstructor
    public TransactionException(BaseExceptionResponse response) {
        super(response, UUID.randomUUID().toString());
        log.error("uuid: {} , code: {}, error: {}",
                super.getUuid(), ErrorCode.TRANSACTION_ERROR, response);
    }

}
