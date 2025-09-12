package com.example.book_api.controllers;

import com.example.book_api.controllers.models.ExceptionResponse;
import com.example.book_api.exceptions.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ExceptionResponse handle(Exception e) {
        return new ExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR)
                .put("error", e.getClass().getSimpleName());
    }

    @ExceptionHandler(BadRequestException.class)
    public ExceptionResponse handleBadRequest(BadRequestException e) {
        return new ExceptionResponse(HttpStatus.BAD_REQUEST)
                .put("error", e.getClass().getSimpleName())
                .put("message", e.getMessage());
    }

    @ExceptionHandler(BindException.class)
    public ExceptionResponse handleBind(BindException be) {
        var res = new ExceptionResponse(HttpStatus.BAD_REQUEST, new HashMap<>(be.getErrorCount()));
        for (var e : be.getAllErrors()) res.put(e.getObjectName(), e.getDefaultMessage());
        return res;
    }
}
