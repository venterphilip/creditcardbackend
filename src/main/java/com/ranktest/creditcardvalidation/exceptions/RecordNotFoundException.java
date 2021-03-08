package com.ranktest.creditcardvalidation.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class RecordNotFoundException extends RuntimeException {

    private String exceptionDetail;
    private Object fieldValue;

    public RecordNotFoundException( String exceptionDetail, String fieldValue) {
        super(exceptionDetail+" - "+fieldValue);
        this.exceptionDetail = exceptionDetail;
        this.fieldValue = fieldValue;
    }

    public String getExceptionDetail() {
        return exceptionDetail;
    }

    public Object getFieldValue() {
        return fieldValue;
    }
}