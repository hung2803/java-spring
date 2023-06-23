package com.vti.storyproject.config.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
public class AppExceptionDto {
    private Instant timestamp;
    private int status;
    private String message;
    private String path;

    public AppExceptionDto(int status, String error){
        this.timestamp = Instant.now();
        this.status = status;
        this.message = error;
    }

    public AppExceptionDto(int status, String message, String path) {
        this.status = status;
        this.message = message;
        this.path = path;
        this.timestamp = Instant.now();
    }

    public AppExceptionDto(ErrorResponseEnum responseEnum){
        this.timestamp = Instant.now();
        this.status = responseEnum.status;
        this.message = responseEnum.massage;
    }
}
