package com.freelance.productservice.exceptions;

import com.freelance.productservice.dtos.ExceptionDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerAdvices {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionDto> handleNotFoundException(NotFoundException notFoundException){
        return new ResponseEntity(
                new ExceptionDto(notFoundException.getMessage(),
                        HttpStatus.NOT_FOUND),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(ArrayIndexOutOfBoundsException.class)
    public ResponseEntity<ExceptionDto> handleNotFoundException(ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException){
        return new ResponseEntity(
                new ExceptionDto(arrayIndexOutOfBoundsException.getMessage(),
                        HttpStatus.NOT_FOUND),
                HttpStatus.NOT_FOUND
        );
    }
}
