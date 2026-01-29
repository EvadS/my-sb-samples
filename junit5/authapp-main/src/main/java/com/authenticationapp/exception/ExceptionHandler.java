package com.authenticationapp.exception;

import org.springframework.core.NestedExceptionUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse onDataIntegrityViolationException(DataIntegrityViolationException ex){
        String message = getMostSpecificMessage(ex);
        return new ExceptionResponse(HttpStatus.BAD_REQUEST, message);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse onIllegalArgumentException(IllegalArgumentException ex){
        String message = ex.getMessage();
        return new ExceptionResponse(HttpStatus.BAD_REQUEST, message);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> onMethodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException) {
        return methodArgumentNotValidException.getBindingResult().getFieldErrors().stream().collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse onDataIntegrityViolationException(HttpMessageNotReadableException ex){
        return new ExceptionResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(UsernameNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse onUsernameNotFoundException(UsernameNotFoundException ex){
        return new ExceptionResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }


    private String getMostSpecificMessage(DataIntegrityViolationException ex) {
        String message = NestedExceptionUtils.getMostSpecificCause(ex).getMessage();
        if(message.contains("Detail:")) {
            message = message.substring(message.indexOf("Detail:")+"Detail:".length());
        }
        return message;
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(InternalAuthenticationServiceException.class)
    public ExceptionResponse handleInternalAuthenticationServiceException(InternalAuthenticationServiceException ex){
        //logger.error("Authentication error "+ex.getMessage());
        return new ExceptionResponse(HttpStatus.BAD_REQUEST, "Invalid Username or Password");
    }
    @org.springframework.web.bind.annotation.ExceptionHandler(BadCredentialsException.class)
    public ExceptionResponse handleBadCredentialsException(BadCredentialsException ex){
        //logger.error("Authentication error "+ex.getMessage());
        return new ExceptionResponse(HttpStatus.BAD_REQUEST, "Invalid Username or Password");
    }
}
