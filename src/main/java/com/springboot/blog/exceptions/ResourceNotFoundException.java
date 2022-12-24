package com.springboot.blog.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends  RuntimeException{
    private  String resourceName;
    private  String fieldName;
    private String fieldvalue;

    public ResourceNotFoundException(String resourceName, String fieldName, String fieldvalue) {
        super(String.format("%s not found with %s : %s", resourceName, fieldName, fieldName));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldvalue = fieldvalue;
    }
}
