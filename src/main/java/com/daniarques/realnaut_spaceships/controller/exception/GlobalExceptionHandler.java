package com.daniarques.realnaut_spaceships.controller.exception;

import com.daniarques.realnaut_spaceships.domain.exception.InvalidParameterException;
import com.daniarques.realnaut_spaceships.domain.exception.NotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidParameterException.class)
    protected ResponseEntity<ErrorResponse> handleInvalidParameterException(
            final HttpServletRequest request,
            final InvalidParameterException exception) {
        log.error("Invalid parameter error. {} ({}{})", exception.getCause(), request.getContextPath(), request.getServletPath());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(this.buildErrorResponse(HttpStatus.BAD_REQUEST, request, exception));
    }


    @ExceptionHandler(NotFoundException.class)
    protected ResponseEntity<ErrorResponse> handleNotFoundException(
            final HttpServletRequest request,
            final NotFoundException exception) {
        log.error("Not found error. {} ({}{})", exception.getCause(), request.getContextPath(), request.getServletPath());

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(this.buildErrorResponse(HttpStatus.NOT_FOUND, request, exception));
    }

    private ErrorResponse buildErrorResponse(final HttpStatus status, final HttpServletRequest request, final Exception exception) {

        return ErrorResponse.builder()
                .status(status.value())
                .error(exception.getMessage())
                .path(request.getRequestURI())
                .build();
    }
}
