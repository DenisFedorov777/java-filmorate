package ru.yandex.practicum.filmorate.model.constraints.exceptions;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

public class InvalidFilmException extends ConstraintViolationException {

    public InvalidFilmException(String message,
                                Set<? extends ConstraintViolation<?>> constraintViolations) {
        super(message, constraintViolations);
    }

    public InvalidFilmException(Set<? extends ConstraintViolation<?>> constraintViolations) {
        super(constraintViolations);
    }
}