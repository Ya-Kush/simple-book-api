package com.example.book_api.controllers;

import com.example.book_api.exceptions.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ExceptionResponse handle(Exception e) {
        return new ExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR)
                .put("error", e.getClass().getSimpleName());
    }

    @ExceptionHandler(BadRequestException.class)
    public ExceptionResponse badRequestHandle(BadRequestException e) {
        return new ExceptionResponse(HttpStatus.BAD_REQUEST)
                .put("error", e.getClass().getSimpleName())
                .put("message", e.getMessage());
    }

    @ExceptionHandler(BindException.class)
    public ExceptionResponse bindHandle(BindException be) {
        var res = new ExceptionResponse(HttpStatus.BAD_REQUEST, new HashMap<>(be.getErrorCount()));
        for (var e : be.getAllErrors()) res.put(e.getObjectName(), e.getDefaultMessage());
        return res;
    }

    public static class ExceptionResponse extends ResponseEntity<Map<String,String>> {
        public ExceptionResponse(HttpStatusCode status) { super(new HashMap<>(), status); }
        public ExceptionResponse(HttpStatusCode status, Map<String, String> body) { super(body, status); }
        public ExceptionResponse(HttpStatusCode status, MultiValueMap<String, String> headers) { super(new HashMap<>(), headers, status); }
        public ExceptionResponse(int rawStatus, Map<String, String> body, MultiValueMap<String, String> headers) { super(body, headers, rawStatus); }
        public ExceptionResponse(HttpStatusCode statusCode, Map<String, String> body, MultiValueMap<String, String> headers) { super(body, headers, statusCode); }

        ExceptionResponse put(String name, String value) { super.getBody().put(name, value); return this; }
    }
}
