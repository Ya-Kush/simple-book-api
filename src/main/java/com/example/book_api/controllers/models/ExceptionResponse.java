package com.example.book_api.controllers.models;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

import java.util.HashMap;
import java.util.Map;

public class ExceptionResponse extends ResponseEntity<Map<String,String>> {
    public ExceptionResponse(HttpStatusCode status) { super(new HashMap<>(), status); }
    public ExceptionResponse(HttpStatusCode status, Map<String, String> body) { super(body, status); }
    public ExceptionResponse(HttpStatusCode status, MultiValueMap<String, String> headers) { super(new HashMap<>(), headers, status); }
    public ExceptionResponse(int rawStatus, Map<String, String> body, MultiValueMap<String, String> headers) { super(body, headers, rawStatus); }
    public ExceptionResponse(HttpStatusCode statusCode, Map<String, String> body, MultiValueMap<String, String> headers) { super(body, headers, statusCode); }

    public ExceptionResponse put(String key, String value) {
        var body = super.getBody();
        if (body != null) body.put(key, value);
        return this;
    }
}
