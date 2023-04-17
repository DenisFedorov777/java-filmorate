package ru.yandex.practicum.filmorate.model.constraints.validators;

import lombok.extern.slf4j.Slf4j;
import ru.yandex.practicum.filmorate.model.constraints.FilmReleaseDateConstrain;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.Month;

@Slf4j
public class FilmReleaseDateValidator implements ConstraintValidator<FilmReleaseDateConstrain, LocalDate> {

    private static final LocalDate RELIES_DATE_FIRST_FILM = LocalDate.of(1895, Month.DECEMBER, 28);

    @Override
    public void initialize(FilmReleaseDateConstrain constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
        return value.isAfter(RELIES_DATE_FIRST_FILM);
    }
}