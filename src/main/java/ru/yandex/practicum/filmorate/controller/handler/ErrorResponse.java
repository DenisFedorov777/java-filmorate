package ru.yandex.practicum.filmorate.controller.handler;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse {

    private int status;
    private String message;
    private LocalDateTime timestamp;
    private String path;
}