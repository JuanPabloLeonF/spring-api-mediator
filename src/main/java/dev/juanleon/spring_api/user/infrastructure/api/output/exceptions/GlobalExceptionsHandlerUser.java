package dev.juanleon.spring_api.user.infrastructure.api.output.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionsHandlerUser {

    @ExceptionHandler(NotFoundUserException.class)
    public ResponseEntity<ProblemDetail> handlerNotFoundUserException(NotFoundUserException exception) {
        ProblemDetail response = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        response.setTitle(HttpStatus.BAD_REQUEST.getReasonPhrase());
        response.setDetail(exception.getMessage());
        response.setProperty("date", LocalDateTime.now());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
    }
}
