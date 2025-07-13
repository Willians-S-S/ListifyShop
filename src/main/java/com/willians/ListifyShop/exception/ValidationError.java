package com.willians.ListifyShop.exception;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ValidationError extends MessageError {
    private List<FieldMesseger> errors = new ArrayList<>();

    public ValidationError() {
    }

    public ValidationError(Instant timestamp, Integer status, String error, String message, String path) {
        super(timestamp, status, error, message, path);
    }

    public void addError(String fieldName, String message){
        errors.add(new FieldMesseger(fieldName, message));
    }

    public List<FieldMesseger> getErrors(){
        return errors;
    }
}
