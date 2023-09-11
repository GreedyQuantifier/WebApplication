package com.example.serviceFakeNews.exception;

import lombok.Getter;

import java.util.Date;


@Getter
public class ApplicationError {
    private int status;
    private String message;
    private Date timestamp;

    public ApplicationError(int status, String message) {
        this.status = status;
        this.message = message;
        timestamp = new Date();
    }
}
