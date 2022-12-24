package com.springboot.blog.contracts;

import lombok.Getter;

import java.util.Date;

@Getter
public class ErrorResponse {
    private Date timeStamp;
    private String message;
    private String details;

    public ErrorResponse(Date timeStamp, String message, String details) {
        this.timeStamp = timeStamp;
        this.message = message;
        this.details = details;
    }
}
