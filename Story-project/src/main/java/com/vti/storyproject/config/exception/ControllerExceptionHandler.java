package com.vti.storyproject.config.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice  // đang try lại các hàm ở controller
public class ControllerExceptionHandler {
    @ExceptionHandler(AppException.class) // đang catch lại các exception là appException ở con troller
    public ResponseEntity<AppException> catchExceptionCustom(AppException exception, HttpServletRequest request) {
        exception.setPath(request.getRequestURI()); // cú pháp để lấy được đường dẫn api
        return ResponseEntity.status(exception.getStatus())
                .body(exception);
    }

    @ExceptionHandler(Exception.class) // đang catch lại các exception là appException là các trường hợp còn lại
    public ResponseEntity<AppException> catchExceptionGlobal(Exception exception, HttpServletRequest request) {
        AppException appException = new AppException();
        appException.setPath(request.getRequestURI());
        appException.setTimestamp(Instant.now());
        appException.setStatus(500);
        appException.setMessage(exception.getMessage());
        return ResponseEntity.status(appException.getStatus())
                .body(appException);
    }
    // Mehtod bắt lỗi validate
    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<AppException> handleBindException(BindException e, HttpServletRequest request) {
        String errorMessage = "";
        if (e.getBindingResult().hasErrors()){
            for(int i=0;i< e.getBindingResult().getAllErrors().size();i++){
                errorMessage += e.getBindingResult().getAllErrors().get(i).getDefaultMessage();
                errorMessage += (i==e.getBindingResult().getAllErrors().size()-1) ? "." : ", ";
            }
        }
        AppException appException = new AppException(400, errorMessage, request.getRequestURI());
        return new ResponseEntity<>(appException, HttpStatus.valueOf(appException.getStatus()));
    }
}
