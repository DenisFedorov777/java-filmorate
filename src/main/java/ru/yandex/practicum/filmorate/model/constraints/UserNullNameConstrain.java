package ru.yandex.practicum.filmorate.model.constraints;

import ru.yandex.practicum.filmorate.model.constraints.validators.UserNullNameValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UserNullNameValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface UserNullNameConstrain {

  String message() default "User name don't validated";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}