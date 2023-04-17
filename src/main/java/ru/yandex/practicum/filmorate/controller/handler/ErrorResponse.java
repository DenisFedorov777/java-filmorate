package ru.yandex.practicum.filmorate.controller.handler;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class ErrorResponse {

    private int status;
    private String message;
    private LocalDateTime timestamp;
    private String path;
}