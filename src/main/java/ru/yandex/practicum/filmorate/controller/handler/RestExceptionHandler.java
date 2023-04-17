package ru.yandex.practicum.filmorate.controller.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.yandex.practicum.filmorate.model.constraints.exceptions.InvalidFilmException;
import ru.yandex.practicum.filmorate.service.exception.NotFoundException;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Slf4j
@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleNotFoundException(NotFoundException ex, HttpServletRequest request) {
        log.warn("The requested resource was not found: {} {} {}",
                request.getMethod(), request.getRequestURI(), request.getQueryString());
        return createErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage(), request.getRequestURI());
    }

    @ExceptionHandler(InvalidFilmException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleFilmReliseException(InvalidFilmException ex, HttpServletRequest request) {
        log.warn("Unable to check movie release date: {} {} {}",
                request.getMethod(), request.getRequestURI(), request.getQueryString());
        return createErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage(), request.getRequestURI());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleInternalServerError(Exception ex, HttpServletRequest request) {
        log.error("An unexpected exception occurred: {}", ex.getMessage(), ex);
        return createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), request.getRequestURI());
    }

    private ErrorResponse createErrorResponse(HttpStatus status, String message, String uri) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatus(status.value());
        errorResponse.setMessage(message);
        errorResponse.setTimestamp(LocalDateTime.now());
        errorResponse.setPath(uri);
        return errorResponse;
    }
}