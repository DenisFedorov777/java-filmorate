package ru.yandex.practicum.filmorate.controller.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.yandex.practicum.filmorate.model.constraints.exceptions.InvalidFilmException;
import ru.yandex.practicum.filmorate.service.exception.NotFoundException;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Slf4j
@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(value = {NotFoundException.class})
    public ResponseEntity<ErrorResponse> handleNotFoundException(
            RuntimeException ex, HttpServletRequest request) {
        log.warn(
                "The requested resource was not found: {} {} {}",
                request.getMethod(),
                request.getRequestURI(),
                request.getQueryString());
        return createErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage(), request.getRequestURI());
    }

    @ExceptionHandler(InvalidFilmException.class)
    public ResponseEntity<ErrorResponse> handleFilmReleaseException(
            InvalidFilmException ex, HttpServletRequest request) {
        log.warn(
                "Unable to check movie release date: {} {} {}",
                request.getMethod(),
                request.getRequestURI(),
                request.getQueryString());
        return createErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage(), request.getRequestURI());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleInternalServerError(
            Exception ex, HttpServletRequest request) {
        log.warn("An unexpected exception occurred: {}", ex.getMessage(), ex);
        return createErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), request.getRequestURI());
    }

    private ResponseEntity<ErrorResponse> createErrorResponse(
            HttpStatus status, String message, String uri) {
        final ErrorResponse response =
                ErrorResponse.builder()
                        .status(status.value())
                        .message(message)
                        .timestamp(LocalDateTime.now())
                        .path(uri)
                        .build();
        return ResponseEntity.status(status).body(response);
    }
}