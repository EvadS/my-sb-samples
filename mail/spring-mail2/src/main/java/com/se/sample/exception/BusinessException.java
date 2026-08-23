package com.se.sample.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import com.se.sample.dto.SubError;

@Getter
@Setter
@AllArgsConstructor
public class BusinessException extends RuntimeException {
    private Object data;
    private List<SubError> subErrors;

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, List<SubError> subErrors) {
        super(message);
        this.subErrors = subErrors;
    }

    public BusinessException(String message, Object data) {
        super(message);
        this.data = data;
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }

    public BusinessException(String message, Throwable cause,
                             boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
