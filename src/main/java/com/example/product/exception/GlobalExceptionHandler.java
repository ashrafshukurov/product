package com.example.product.exception;

import com.example.product.dto.ErrorResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

/**
 * @author Ashraf on 19-Jul-23
 * @project customer
 */

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> notFoundException(NotFoundException ex){
        ErrorResponse errorResponse=new ErrorResponse();
        errorResponse.setErrorCode(ex.getHttpStatus().value());
        errorResponse.setErrorMessage(ex.getMessage());
        errorResponse.setStatus(ex.getHttpStatus());
        errorResponse.setLocalDateTime(LocalDateTime.now());
        return ResponseEntity.status(ex.getHttpStatus()).body(errorResponse);
    }
    @ExceptionHandler(InSufficientCount.class)
    public ResponseEntity<?> inSufficientBalance(InSufficientCount ex){
        ErrorResponse errorResponse=new ErrorResponse();
        errorResponse.setErrorCode(ex.getHttpStatus().value());
        errorResponse.setErrorMessage(ex.getMessage());
        errorResponse.setStatus(ex.getHttpStatus());
        return ResponseEntity.status(ex.getHttpStatus()).body(errorResponse);
    }
}
