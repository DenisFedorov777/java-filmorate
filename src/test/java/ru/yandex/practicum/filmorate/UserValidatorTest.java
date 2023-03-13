package ru.yandex.practicum.filmorate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.validator.UserValidator;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class UserValidatorTest {
    private User.UserBuilder builder;
    private User user;

    @BeforeEach
    public void beforeEach() {
        builder = User.builder();
    }

    @Test
    public void shouldThrowExceptionWhenBirthdayIsInFuture() {
        user = builder.email("Sonya2023@yandex.ru").login("СофьяПожарская").name("Соня")
                .birthday(LocalDate.now().plusWeeks(2)).build();
        assertThrows(ValidationException.class, () -> UserValidator.validate(user));
    }

    @Test
    public void shouldThrowExceptionWhenEmailIsNull() {
        user = builder.email(null).login("СофьяПожарская").name("Соня")
                .birthday(LocalDate.of(1997, 12, 19)).build();
        assertThrows(ValidationException.class, () -> UserValidator.validate(user));
    }

    @Test
    public void shouldSuccessfullyValidateCorrectUser() {
        user = builder.email("Sonya2023@yandex.ru").login("СофьяПожарская").name("Соня")
                .birthday(LocalDate.of(1997, 12, 19)).build();
        assertDoesNotThrow(() -> UserValidator.validate(user));
    }

    @Test
    public void shouldThrowExceptionWhenEmailIsWithoutAtSymbol() {
        user = builder.email("Sonya2023yandex.ru").login("СофьяПожарская").name("Соня")
                .birthday(LocalDate.of(1997, 12, 19)).build();
        assertThrows(ValidationException.class, () -> UserValidator.validate(user));
    }

    @Test
    public void shouldThrowExceptionWhenEmailIsEmpty() {
        user = builder.email("").login("СофьяПожарская").name("Соня")
                .birthday(LocalDate.of(1997, 12, 19)).build();
        assertThrows(ValidationException.class, () -> UserValidator.validate(user));
    }

    @Test
    public void shouldNotThrowExceptionWhenBirthdayIsToday() {
        user = builder.email("Sonya2023@yandex.ru").login("СофьяПожарская").name("Соня")
                .birthday(LocalDate.now()).build();
        assertDoesNotThrow(() -> UserValidator.validate(user));
    }

    @Test
    public void shouldThrowExceptionWhenLoginIsNull() {
        user = builder.email("Sonya2023@yandex.ru").login(null).name("Соня")
                .birthday(LocalDate.of(1997, 12, 19)).build();
        assertThrows(ValidationException.class, () -> UserValidator.validate(user));
    }

    @Test
    public void shouldThrowExceptionWhenLoginContainsSpace() {
        user = builder.email("Sonya2023@yandex.ru").login("Софья Пожарская").name("Соня")
                .birthday(LocalDate.of(1997, 12, 19)).build();
        assertThrows(ValidationException.class, () -> UserValidator.validate(user));
    }

    @Test
    public void shouldThrowExceptionWhenLoginIsEmpty() {
        user = builder.email("Sonya2023@yandex.ru").login("").name("Соня")
                .birthday(LocalDate.of(1997, 12, 19)).build();
        assertThrows(ValidationException.class, () -> UserValidator.validate(user));
    }
}