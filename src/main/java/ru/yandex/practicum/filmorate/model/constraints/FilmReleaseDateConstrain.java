package ru.yandex.practicum.filmorate.model.constraints;

import ru.yandex.practicum.filmorate.model.constraints.exceptions.InvalidFilmException;
import ru.yandex.practicum.filmorate.model.constraints.validators.FilmReleaseDateValidator;

import javax.validation.Constraint;
import javax.validation.ConstraintViolationException;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = FilmReleaseDateValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface FilmReleaseDateConstrain {

    String message() default "Data release validate invalid";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    Class<? extends ConstraintViolationException> exception() default InvalidFilmException.class;
}