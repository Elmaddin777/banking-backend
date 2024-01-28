package com.gwpublic.demo.error.handler;


import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.gwpublic.demo.error.CustomerException;
import com.gwpublic.demo.error.TransactionException;
import com.gwpublic.demo.error.response.ErrorCode;
import com.gwpublic.demo.error.response.ErrorResponse;
import feign.FeignException;
import feign.RetryableException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
@Slf4j
public class ErrorHandler {

    private static final String ERROR_PATTERN = "uuid: {}, code: {}, error: {}";

    @ExceptionHandler({
            MissingServletRequestParameterException.class,
            HttpRequestMethodNotSupportedException.class,
            MissingRequestHeaderException.class,
            MethodArgumentTypeMismatchException.class,
            InvalidFormatException.class
    })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleStandardControllerBadRequestExceptions(Exception e) {
        var uuid = UUID.randomUUID().toString();
        log.error(ERROR_PATTERN, uuid,
                ErrorCode.REQUEST_NOT_VALID, e.getMessage());
        return new ErrorResponse(ErrorCode.REQUEST_NOT_VALID, e.getMessage(), uuid);
    }

    @ExceptionHandler({
            MethodArgumentNotValidException.class
    })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleValidationBadRequestExceptions(MethodArgumentNotValidException e) {
        var uuid = UUID.randomUUID().toString();
        log.error(ERROR_PATTERN, uuid,
                ErrorCode.REQUEST_NOT_VALID, e.getMessage());
        StringBuilder errors = new StringBuilder();
        e.getBindingResult().getFieldErrors().forEach(error -> {
            errors.append(error.getDefaultMessage()).append(";");
        });
        return new ErrorResponse(ErrorCode.REQUEST_NOT_VALID, errors.toString(), uuid);
    }

    @ExceptionHandler(CustomerException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handle(CustomerException e) {
        return new ErrorResponse(ErrorCode.CUSTOMER_ERROR,
                HttpStatus.BAD_REQUEST.getReasonPhrase(), e.getUuid());
    }

    @ExceptionHandler(TransactionException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handle(TransactionException e) {
        return new ErrorResponse(ErrorCode.TRANSACTION_ERROR,
                HttpStatus.BAD_REQUEST.getReasonPhrase(), e.getUuid());
    }

  /*  @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handle(Exception e) {
        var uuid = UUID.randomUUID().toString();
        log.error(ERROR_PATTERN, uuid,
                ErrorCode.INTERNAL_SERVER_ERROR, e.getMessage());
        return new ErrorResponse(ErrorCode.INTERNAL_SERVER_ERROR, "Internal Server Error", uuid);
    }*/

    @ExceptionHandler(FeignException.class)
    @ResponseStatus(HttpStatus.FAILED_DEPENDENCY)
    public ErrorResponse handle(FeignException e) {
        var uuid = UUID.randomUUID().toString();
        log.error(ERROR_PATTERN, uuid,
                ErrorCode.REQUEST_NOT_VALID, e.getMessage());
        if (e instanceof RetryableException && e.getCause() instanceof SocketTimeoutException) {
            return new ErrorResponse(ErrorCode.SERVICE_PROVIDER_READ_TIMEOUT,
                    "Read timeout", uuid);
        } else if (e instanceof RetryableException && e.getCause() instanceof ConnectException) {
            return new ErrorResponse(ErrorCode.SERVICE_PROVIDER_CONNECT_TIMEOUT,
                    "Connection timeout", uuid);
        } else {
            return new ErrorResponse(ErrorCode.SERVICE_PROVIDER_ERROR,
                    String.format("Error occurred: - %s", e.getMessage()), uuid);
        }
    }

}